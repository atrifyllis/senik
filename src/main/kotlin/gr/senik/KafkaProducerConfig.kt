package gr.senik

import org.springframework.beans.factory.ObjectProvider
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean
import org.springframework.boot.autoconfigure.kafka.KafkaProperties
import org.springframework.boot.context.properties.PropertyMapper
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.kafka.core.ProducerFactory
import org.springframework.kafka.support.ProducerListener
import org.springframework.kafka.support.converter.RecordMessageConverter

@Configuration
class KafkaProducerConfig {

    /**
     * Copied from {@see KafkaAutoConfiguration} just to enable observation.
     */
    @Bean
    @ConditionalOnMissingBean(KafkaTemplate::class)
    fun kafkaTemplate(
        kafkaProducerFactory: ProducerFactory<Any, Any>,
        kafkaProducerListener: ProducerListener<Any, Any>,
        messageConverter: ObjectProvider<RecordMessageConverter?>,
        properties: KafkaProperties
    ): KafkaTemplate<*, *> {
        val map = PropertyMapper.get().alwaysApplyingWhenNonNull()
        val kafkaTemplate = KafkaTemplate(kafkaProducerFactory)
        messageConverter.ifUnique { kafkaTemplate.setMessageConverter(it!!) }
        map.from(kafkaProducerListener).to(kafkaTemplate::setProducerListener)
        map.from<String>(properties.template.defaultTopic).to(kafkaTemplate::setDefaultTopic)
        map.from<String>(properties.template.transactionIdPrefix).to(kafkaTemplate::setTransactionIdPrefix)
        kafkaTemplate.setObservationEnabled(true) // TODO any easier way to customise auto-configured kafkaTemplate?
        return kafkaTemplate
    }

}
