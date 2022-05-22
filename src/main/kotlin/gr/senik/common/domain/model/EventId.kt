package gr.senik.common.domain.model

import java.util.*
import javax.persistence.Embeddable

@Embeddable
class EventId(id: UUID?) : DomainEntityId(id)
