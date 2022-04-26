package gr.fnik.common.domain.model

import com.fasterxml.jackson.annotation.JsonIgnore
import com.vladmihalcea.hibernate.type.json.JsonType
import org.hibernate.annotations.TypeDef
import org.hibernate.annotations.TypeDefs
import org.jmolecules.ddd.types.AggregateRoot
import org.jmolecules.ddd.types.Identifier
import org.springframework.data.domain.AfterDomainEventPublication
import org.springframework.data.domain.DomainEvents
import java.util.*
import javax.persistence.MappedSuperclass
import javax.persistence.Transient

/**
 * Base class for aggregate roots.
 *
 * @param <ID> the aggregate root ID type.
</ID> */
@MappedSuperclass
@TypeDefs(TypeDef(name = "json", typeClass = JsonType::class))
abstract class AbstractAggregateRoot<T : AggregateRoot<T, ID>, ID : Identifier> protected constructor() :
    BaseAggregate<T, ID>() {

    @Transient
    @JsonIgnore
    private val domainEvents: MutableList<DomainEvent> = ArrayList()

    /**
     * Registers the given domain event to be published when the aggregate root is persisted.
     * NOTE: this method needs to be final since it is called from init blocks in subclasses.
     *
     * @param event the event to register.
     */
    protected final fun registerEvent(event: DomainEvent) {
        Objects.requireNonNull(event, "event must not be null")
        domainEvents.add(event)
    }

    /**
     * Called by the persistence framework to clear all registered domain events once they have been published.
     */
    @AfterDomainEventPublication
    protected fun clearDomainEvents() {
        domainEvents.clear()
    }

    /**
     * Returns all domain events that have been registered for publication. Intended to be used by the persistence
     * framework only.
     */
    @DomainEvents
    protected fun domainEvents(): Collection<Any> {
        return Collections.unmodifiableList<Any>(domainEvents)
    }
}
