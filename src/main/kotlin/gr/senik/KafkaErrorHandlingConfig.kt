package gr.senik

import jakarta.validation.ValidationException
import org.apache.kafka.clients.consumer.Consumer
import org.apache.kafka.clients.consumer.ConsumerRecord
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.kafka.listener.*
import org.springframework.kafka.support.ExponentialBackOffWithMaxRetries
import org.springframework.util.backoff.BackOff
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean


const val CALCULATION_COMMANDS_DLT = "calculation.commands.DLT"

@Configuration
class KafkaErrorHandlingConfig(
    @Autowired
    private var validator: LocalValidatorFactoryBean,
) {
    /**
     * Extends DefaultErrorHandler to also log exception (otherwise message is sent to dead letter topic and exception
     * is never logged).
     */
    class DefaultLoggingErrorHandler(
        recoverer: ConsumerRecordRecoverer,
        backOff: BackOff,
        private val commonLoggingErrorHandler: CommonLoggingErrorHandler
    ) : DefaultErrorHandler(recoverer, backOff) {
        override fun handleOne(
            thrownException: Exception,
            record: ConsumerRecord<*, *>,
            consumer: Consumer<*, *>,
            container: MessageListenerContainer
        ): Boolean {

            commonLoggingErrorHandler.handleOne(thrownException, record, consumer, container)

            return super.handleOne(thrownException, record, consumer, container)
        }
    }

    /**
     * Used by our custom error handler bellow.
     */
    @Bean
    fun commonLoggingErrorHandler(): CommonLoggingErrorHandler {
        return CommonLoggingErrorHandler()
    }

    /**
     * Custom error handler which:
     * 1) retries with exponential backoff
     * 2) logs exception
     * 3) publishes message in dead letter topic.
     */
    @Bean
    fun defaultErrorHandler(
        kafkaTemplate: KafkaTemplate<String, Any>,
        commonLoggingErrorHandler: CommonLoggingErrorHandler
    ): DefaultErrorHandler {
        // Publish to dead letter topic any messages dropped after retries with back off
        val recoverer = DeadLetterPublishingRecoverer(kafkaTemplate)
        // Spread out attempts over time, taking a little longer between each attempt
        // Set a max for retries below max.poll.interval.ms; default: 5m, as otherwise we trigger a consumer rebalance
        val exponentialBackOff = ExponentialBackOffWithMaxRetries(3)
        exponentialBackOff.initialInterval = 500L
        exponentialBackOff.multiplier = 1.5
        exponentialBackOff.maxInterval = 2000
        val errorHandler = DefaultLoggingErrorHandler(recoverer, exponentialBackOff, commonLoggingErrorHandler)
        // Do not try to recover from validation exceptions when validation has failed
        errorHandler.addNotRetryableExceptions(ValidationException::class.java)
        return errorHandler
    }
}
