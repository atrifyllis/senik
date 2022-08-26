package gr.senik.netcalculator.domain.model.tax.selfemployedcontribution

import gr.senik.common.domain.model.AbstractAggregateRoot
import gr.senik.common.domain.model.DomainEntityId
import gr.senik.common.domain.model.Money
import java.util.*
import javax.persistence.*

@Embeddable
class SelfEmployedContributionId(id: UUID?) : DomainEntityId(id)

@Entity
class SelfEmployedContribution(
    @Enumerated(EnumType.STRING)
    val type: SECType,

    @Embedded
    @AttributeOverride(name = "amount", column = Column(name = "amount"))
    @AttributeOverride(name = "currencyCode", column = Column(name = "currency"))
    val amount: Money,

    ) : AbstractAggregateRoot<SelfEmployedContribution, SelfEmployedContributionId>() {
    @EmbeddedId
    override val id: SelfEmployedContributionId = SelfEmployedContributionId(UUID.randomUUID())
}
