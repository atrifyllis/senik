package gr.senik.common.domain.model

import jakarta.persistence.Embeddable
import java.util.*

@Embeddable
class EventId(id: UUID) : DomainEntityId(id)
