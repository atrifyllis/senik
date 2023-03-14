package gr.senik.netcalculator.adapters.secondary.persistence

import jakarta.persistence.EntityManager
import jakarta.persistence.PersistenceContext
import org.apache.kafka.clients.consumer.Consumer
import org.apache.kafka.clients.consumer.ConsumerRecord
import org.springframework.kafka.listener.RecordInterceptor
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import java.util.*

const val EVENT_ID_HEADER = "eventId"

@Component
open class IdempotentConsumerRecordInterceptor<T>(
    @PersistenceContext val em: EntityManager,
) : RecordInterceptor<String, T> {

    /**
     * Use EntityManager to call persist method directly, instead of a JPA repository to avoid updating existing entity.
     * This way the insert fails for sure.
     */
    @Transactional
    override fun intercept(record: ConsumerRecord<String, T>, consumer: Consumer<String, T>): ConsumerRecord<String, T>? {
        val eventId = extractHeader(record, EVENT_ID_HEADER)
        em.persist(ProcessedEventEntity(UUID.fromString(eventId)))
        return record
    }

    private fun extractHeader(record: ConsumerRecord<String, T>, headerName: String) =
        (record.headers().lastHeader(headerName)?.value()?.toString(Charsets.UTF_8)
            ?: throw IllegalStateException())
}

