package gr.senik.netcalculator

import gr.senik.netcalculator.application.ports.`in`.dto.CalculationCommand
import org.apache.kafka.clients.consumer.Consumer
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.kafka.core.ConsumerFactory
import org.springframework.test.context.DynamicPropertyRegistry
import org.springframework.test.context.DynamicPropertySource
import org.testcontainers.containers.KafkaContainer
import org.testcontainers.junit.jupiter.Container
import org.testcontainers.utility.DockerImageName
import java.util.*


class KafkaTestBase(
    @Autowired
    private val consumerFactory: ConsumerFactory<String, CalculationCommand>,
) : IntegrationTestBase() {

    companion object {
        @Container
        private val kafka: KafkaContainer = KafkaContainer(DockerImageName.parse("confluentinc/cp-kafka:6.2.1"))

        @JvmStatic
        @DynamicPropertySource
        fun kafkaProperties(registry: DynamicPropertyRegistry) {
            registry.add("spring.kafka.bootstrap-servers", kafka::getBootstrapServers)
        }
    }

    final fun testConsumer(groupIdPrefix: String, topic: String): Consumer<String, CalculationCommand> {
        val createConsumer = consumerFactory.createConsumer("$groupIdPrefix-${UUID.randomUUID()}", "clId", "", consumerProperties())
        createConsumer.subscribe(mutableListOf(topic))
        return createConsumer
    }

    private fun consumerProperties(): Properties {
        val properties = Properties()
        properties["auto.offset.reset"] = "earliest" // otherwise consumer reads again the same messages between tests
        return properties
    }
}
