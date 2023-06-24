package gr.senik

import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.ObjectMapper
import gr.alx.common.domain.model.DomainEvent
import org.springframework.beans.factory.ObjectProvider
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean
import org.springframework.boot.autoconfigure.kafka.ConcurrentKafkaListenerContainerFactoryConfigurer
import org.springframework.boot.autoconfigure.kafka.DefaultKafkaConsumerFactoryCustomizer
import org.springframework.boot.autoconfigure.kafka.KafkaProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.annotation.EnableKafka
import org.springframework.kafka.annotation.KafkaListenerConfigurer
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory
import org.springframework.kafka.config.KafkaListenerEndpointRegistrar
import org.springframework.kafka.core.ConsumerFactory
import org.springframework.kafka.core.DefaultKafkaConsumerFactory
import org.springframework.kafka.listener.DefaultErrorHandler
import org.springframework.kafka.support.serializer.ErrorHandlingDeserializer
import org.springframework.kafka.support.serializer.JsonDeserializer
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean

const val CALCULATION_COMMANDS = "calculation.commands"

@Configuration
@EnableKafka
class KafkaListenerConfig(
    @Autowired
    private var validator: LocalValidatorFactoryBean,
) : KafkaListenerConfigurer {

    /**
     * This is not auto-configured for some reason, so no validation is performed without it.
     */
    override fun configureKafkaListeners(registrar: KafkaListenerEndpointRegistrar) {
        registrar.setValidator(this.validator)
    }

    /**
     * Copied from {@see KafkaAutoConfiguration} to enable observation AND use our custom error handler.
     */
    @Bean
    @ConditionalOnMissingBean(name = ["kafkaListenerContainerFactory"])
    fun kafkaListenerContainerFactory(
        configurer: ConcurrentKafkaListenerContainerFactoryConfigurer,
        kafkaConsumerFactory: ObjectProvider<ConsumerFactory<Any?, Any?>>,
        properties: KafkaProperties, errorHandler: DefaultErrorHandler
    ): ConcurrentKafkaListenerContainerFactory<*, *> {
        val factory = ConcurrentKafkaListenerContainerFactory<Any, Any>()
        configurer.configure(factory, kafkaConsumerFactory
            .getIfAvailable { DefaultKafkaConsumerFactory<Any?, Any?>(properties.buildConsumerProperties()) })
        // TODO any easier way to customise auto-configured kafkaListenerContainerFactory?
        factory.containerProperties.isObservationEnabled = true
        factory.setCommonErrorHandler(errorHandler) // otherwise our custom error handler is not used!
        return factory
    }

    /**
     * Creates custom JsonDeserializer that uses Spring Boot's objectMapper and TypeReference which results in usage of
     * the {@see DomainEventMixIn}. Moreover, it is wrapped by ErrorHandlingDeserializer, it was the only way to
     * leverage the DefaultErrorHandler configured above.
     *
     * TODO Could not find a way to use spring boot objectMapper, which relies on the DomainMixin to ser/deserialize event.
     */
    @Bean
    fun kafkaConsumerFactory(
        customizers: ObjectProvider<DefaultKafkaConsumerFactoryCustomizer>,
        properties: KafkaProperties,
        objectMapper: ObjectMapper
    ): DefaultKafkaConsumerFactory<*, *> {
        val factory = DefaultKafkaConsumerFactory<String, DomainEvent>(properties.buildConsumerProperties())
        customizers.orderedStream()
            .forEach { customizer: DefaultKafkaConsumerFactoryCustomizer -> customizer.customize(factory) }
        factory.setValueDeserializer(ErrorHandlingDeserializer(JsonDeserializer(object :
            TypeReference<DomainEvent>() {}, objectMapper)))
        return factory
    }
}
