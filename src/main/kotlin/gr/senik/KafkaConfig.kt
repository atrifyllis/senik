package gr.senik

import jakarta.validation.ValidationException
import mu.KotlinLogging
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.annotation.EnableKafka
import org.springframework.kafka.annotation.KafkaListenerConfigurer
import org.springframework.kafka.config.KafkaListenerEndpointRegistrar
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.kafka.listener.DeadLetterPublishingRecoverer
import org.springframework.kafka.listener.DefaultErrorHandler
import org.springframework.kafka.support.ExponentialBackOffWithMaxRetries
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean


const val CALCULATION_COMMANDS = "calculation.commands"
const val CALCULATION_COMMANDS_DLT = "calculation.commands.DLT"

private val log = KotlinLogging.logger {}


@Configuration
@EnableKafka
class KafkaConfig(
    @Autowired
    private var validator: LocalValidatorFactoryBean,
) : KafkaListenerConfigurer {

    /**
     * This is not auto-configured for some reason, so no validation is performed without it.
     */
    override fun configureKafkaListeners(registrar: KafkaListenerEndpointRegistrar) {
        registrar.setValidator(this.validator)
    }

    @Bean
    fun defaultErrorHandler(kafkaTemplate: KafkaTemplate<String, Any>): DefaultErrorHandler {
        // Publish to dead letter topic any messages dropped after retries with back off
        val recoverer = DeadLetterPublishingRecoverer(kafkaTemplate)
        // Spread out attempts over time, taking a little longer between each attempt
        // Set a max for retries below max.poll.interval.ms; default: 5m, as otherwise we trigger a consumer rebalance
        val exponentialBackOff = ExponentialBackOffWithMaxRetries(3)
        exponentialBackOff.initialInterval = 500L
        exponentialBackOff.multiplier = 1.5
        exponentialBackOff.maxInterval = 2000
        val errorHandler = DefaultErrorHandler(recoverer, exponentialBackOff)
        // Do not try to recover from validation exceptions when validation has failed
        errorHandler.addNotRetryableExceptions(ValidationException::class.java)
        return errorHandler
    }
}
