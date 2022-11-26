package gr.senik.common.domain.model

import java.util.*
import jakarta.persistence.Embeddable

@Embeddable
class EventId(id: UUID?) : DomainEntityId(id)
