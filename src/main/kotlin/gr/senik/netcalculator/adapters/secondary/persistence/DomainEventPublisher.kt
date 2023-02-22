package gr.senik.netcalculator.adapters.secondary.persistence

import gr.senik.common.domain.model.AbstractAggregateRoot
import org.jmolecules.ddd.types.Identifier
import org.springframework.context.ApplicationEventPublisher
import org.springframework.stereotype.Component

@Component
class DomainEventPublisher(
    private val applicationEventPublisher: ApplicationEventPublisher
) {

    fun <T : AbstractAggregateRoot<T, ID>, ID : Identifier> publishEvents(aggregate: T) {
        aggregate.domainEvents().forEach { applicationEventPublisher.publishEvent(it) }
        aggregate.clearDomainEvents()
    }
}
