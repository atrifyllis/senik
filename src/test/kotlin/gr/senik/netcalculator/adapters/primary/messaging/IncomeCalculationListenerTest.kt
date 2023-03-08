package gr.senik.netcalculator.adapters.primary.messaging

import gr.alx.common.domain.model.Money
import gr.senik.CALCULATION_COMMANDS
import gr.senik.CALCULATION_COMMANDS_DLT
import gr.senik.netcalculator.KafkaTestBase
import gr.senik.netcalculator.adapters.secondary.persistence.CalculatedIncomeRepository
import gr.senik.netcalculator.adapters.secondary.persistence.EVENT_ID_HEADER
import gr.senik.netcalculator.adapters.secondary.persistence.IndividualEntity
import gr.senik.netcalculator.application.ports.`in`.dto.CalculationCommand
import gr.senik.netcalculator.application.ports.`in`.dto.IndividualDto
import gr.senik.netcalculator.domain.model.DailyIncome
import gr.senik.netcalculator.domain.model.InsuranceType
import org.apache.kafka.clients.producer.ProducerRecord
import org.apache.kafka.common.header.internals.RecordHeader
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.kafka.core.ConsumerFactory
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.kafka.test.utils.KafkaTestUtils
import org.springframework.messaging.MessageHeaders
import org.testcontainers.shaded.org.awaitility.Awaitility.await
import java.time.Duration
import java.util.*

private const val KEY_1 = "key-1"
private const val KEY_2 = "key-2"
private const val KEY_3 = "key-3"

class IncomeCalculationListenerTest(
    @Autowired
    private val kafkaTemplate: KafkaTemplate<String, CalculationCommand>,
    @Autowired
    private val calculatedIncomeRepository: CalculatedIncomeRepository,
    @Autowired
    private val consumerFactory: ConsumerFactory<String, CalculationCommand>,
) : KafkaTestBase(consumerFactory) {

    private val calculationCommandConsumer = testConsumer("test-calculation-command-group", CALCULATION_COMMANDS)
    private val calculationCommandDltConsumer = testConsumer("test-calculation-command-dlt-group", CALCULATION_COMMANDS_DLT)

    @Test
    fun `should listen to calculation message and store calculation in database`() {
        // given:
        val calculationCommand = calculationCommand(CALCULATION_COMMANDS, KEY_1, 1, UUID.randomUUID().toString().toByteArray())
        // when:
        val headers: MessageHeaders
        kafkaTemplate.send(calculationCommand)
        // then:
        val records = KafkaTestUtils.getRecords(calculationCommandConsumer).records(CALCULATION_COMMANDS)
        assertThat(records.any { r -> r.key() == KEY_1 }).isTrue

        // Wait for the message to be processed by the Kafka listener
        await().atMost(Duration.ofSeconds(5)).untilAsserted {
            val calculatedIncomeEntities = calculatedIncomeRepository.findAll()
            // Verify that the expected data was written to the database
            assertThat(calculatedIncomeEntities).hasSize(1)
            assertThat(calculatedIncomeEntities[0].individual.annualExpensesAmount).isEqualTo(Money.ZERO)
        }
    }

    @Test
    fun `should listen to invalid calculation message and not process it`() {
        // given:
        val calculationCommand = calculationCommand(CALCULATION_COMMANDS, KEY_2, 10000, UUID.randomUUID().toString().toByteArray())
        // when:
        kafkaTemplate.send(calculationCommand)
        // then:
        val records = KafkaTestUtils.getRecords(calculationCommandConsumer).records(CALCULATION_COMMANDS)
        assertThat(records.any { r -> r.key() == KEY_2 }).isTrue

        // check that invalid message was sent to DLT
        val dltRecords = KafkaTestUtils.getRecords(calculationCommandDltConsumer).records(CALCULATION_COMMANDS_DLT)
        assertThat(dltRecords).hasSize(1)
        assertThat(dltRecords.first().key()).isEqualTo(KEY_2)

        // Wait for the message to be processed by the Kafka listener
        await().atMost(Duration.ofSeconds(5)).untilAsserted {
            val calculatedIncomeEntities = calculatedIncomeRepository.findAll()
            // Verify that no data has been written to the database
            assertThat(calculatedIncomeEntities).hasSize(0)
        }
    }

    @Test
    fun `should not process duplicate message`() {
        // given:
        val eventId = UUID.randomUUID().toString().toByteArray()
        val calculationCommand = calculationCommand(CALCULATION_COMMANDS, KEY_1, 1, eventId)
        val calculationCommand2 = calculationCommand(CALCULATION_COMMANDS, KEY_1, 100, eventId)
        // when:
        val headers: MessageHeaders
        kafkaTemplate.send(calculationCommand)
        kafkaTemplate.send(calculationCommand2)
        // then:
        val records = KafkaTestUtils.getRecords(calculationCommandConsumer).records(CALCULATION_COMMANDS)
        assertThat(records.any { r -> r.key() == KEY_1 }).isTrue

        // TODO that second message is NOT sent to DLT, not sure why

        // Wait for the message to be processed by the Kafka listener
        await().atMost(Duration.ofSeconds(5)).untilAsserted {
            val calculatedIncomeEntities = calculatedIncomeRepository.findAll()
            // Verify that the expected data was written to the database once
            assertThat(calculatedIncomeEntities).hasSize(1)
            assertThat(calculatedIncomeEntities[0].individual.annualExpensesAmount).isEqualTo(Money.ZERO)
        }
    }


    @Test
    fun `should process non duplicate message`() {
        // given:
        val eventId = UUID.randomUUID().toString().toByteArray()
        val calculationCommand = calculationCommand(CALCULATION_COMMANDS, KEY_1, 1, eventId)
        val calculationCommand2 = calculationCommand(CALCULATION_COMMANDS, KEY_3, 10, UUID.randomUUID().toString().toByteArray())
        // when:
        val headers: MessageHeaders
        kafkaTemplate.send(calculationCommand)
        kafkaTemplate.send(calculationCommand2)
        // then:
        val records = KafkaTestUtils.getRecords(calculationCommandConsumer).records(CALCULATION_COMMANDS)
        assertThat(records.any { r -> r.key() == KEY_1 }).isTrue

        // Wait for the message to be processed by the Kafka listener
        await().atMost(Duration.ofSeconds(5)).untilAsserted {
            val calculatedIncomeEntities = calculatedIncomeRepository.findAll()
            // Verify that the expected data was written to the database once
            assertThat(calculatedIncomeEntities).hasSize(2)
            val individuals: List<IndividualEntity> = calculatedIncomeEntities.map { it.individual }
            assertThat(individuals).extracting<Int> { it.branches }.contains(1, 10)
        }
    }

    private fun calculationCommand(topic: String, key: String, branches: Int, eventId: ByteArray): ProducerRecord<String, CalculationCommand> {
        val producerRecord = ProducerRecord(
            topic,
            key,
            CalculationCommand(
                individual = IndividualDto(
                    insuranceType = InsuranceType.TSMEDE,
                    efkaClassId = UUID.fromString("d55ec320-c0fe-4222-808e-3b52d9087061"),
                    eteaepClassId = UUID.fromString("14d0b02a-2898-4c7b-8519-3bf163f8f931"),
                    grossDailyIncomes = listOf(DailyIncome(days = 220, dailyIncome = Money(370))),
                    annualExpensesAmount = Money.ZERO,
                    grossAnnualIncome = null,
                    branches = branches
                )
            )
        )
        producerRecord.headers().add(RecordHeader(EVENT_ID_HEADER, eventId))
        return producerRecord
    }
}
