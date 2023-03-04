package gr.senik.netcalculator.adapters.primary.messaging

import gr.alx.common.domain.model.Money
import gr.senik.CALCULATION_COMMANDS
import gr.senik.netcalculator.KafkaTestBase
import gr.senik.netcalculator.adapters.secondary.persistence.CalculatedIncomeRepository
import gr.senik.netcalculator.application.ports.`in`.dto.CalculationCommand
import gr.senik.netcalculator.application.ports.`in`.dto.IndividualDto
import gr.senik.netcalculator.domain.model.DailyIncome
import gr.senik.netcalculator.domain.model.InsuranceType
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.kafka.core.ConsumerFactory
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.kafka.test.utils.KafkaTestUtils
import org.testcontainers.shaded.org.awaitility.Awaitility.await
import java.time.Duration
import java.util.*

private const val KEY_1 = "key-1"
private const val KEY_2 = "key-2"

class IncomeCalculationListenerTest(
    @Autowired
    private val kafkaTemplate: KafkaTemplate<String, CalculationCommand>,
    @Autowired
    private val calculatedIncomeRepository: CalculatedIncomeRepository,
    @Autowired
    private val consumerFactory: ConsumerFactory<String, CalculationCommand>,
) : KafkaTestBase(consumerFactory) {

    private val consumer = testConsumer("test-calculation-command-group", CALCULATION_COMMANDS)

    @Test
    fun `should listen to calculation message and store calculation in database`() {
        // given:
        val calculationCommand = calculationCommand(1)
        // when:
        kafkaTemplate.send(CALCULATION_COMMANDS, KEY_1, calculationCommand)
        // then:
        val records = KafkaTestUtils.getRecords(consumer).records(CALCULATION_COMMANDS);
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
    fun `should listen to invalid calculation message and not process it `() {
        // given:
        val calculationCommand = calculationCommand(10000)
        // when:
        kafkaTemplate.send(CALCULATION_COMMANDS, KEY_2, calculationCommand)
        // then:
        val records = KafkaTestUtils.getRecords(consumer).records(CALCULATION_COMMANDS);
        assertThat(records.any { r -> r.key() == KEY_2 }).isTrue

        // Wait for the message to be processed by the Kafka listener
        await().atMost(Duration.ofSeconds(5)).untilAsserted {
            val calculatedIncomeEntities = calculatedIncomeRepository.findAll()
            // Verify that no data has been written to the database
            assertThat(calculatedIncomeEntities).hasSize(0)
        }
    }

    private fun calculationCommand(branches: Int) = CalculationCommand(
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
}
