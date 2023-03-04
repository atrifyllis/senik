package gr.senik

import mu.KotlinLogging
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.annotation.EnableKafka
import org.springframework.kafka.annotation.KafkaListenerConfigurer
import org.springframework.kafka.config.KafkaListenerEndpointRegistrar
import org.springframework.kafka.listener.KafkaListenerErrorHandler
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean

val CALCULATION_COMMANDS = "calculation.commands"

private val log = KotlinLogging.logger {}


@Configuration
@EnableKafka
class KafkaConfig(
    @Autowired
    private var validator: LocalValidatorFactoryBean,
) : KafkaListenerConfigurer {

    override fun configureKafkaListeners(registrar: KafkaListenerEndpointRegistrar) {
        registrar.setValidator(this.validator);
    }

    // TODO handle errors with DLT
    @Bean
    fun validationErrorHandler(): KafkaListenerErrorHandler {
        return KafkaListenerErrorHandler { _, exception ->
            log.warn { exception }
            log.warn { exception.cause }
        }
    }
}
