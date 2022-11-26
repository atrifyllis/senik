package gr.senik.netcalculator.domain.model.tax.selfemployedcontribution

import gr.senik.common.domain.model.DomainEntityId
import gr.senik.common.domain.model.Money
import gr.senik.netcalculator.domain.model.income.CalculatedNetIncome
import java.util.*
import jakarta.persistence.*

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

    ) : org.jmolecules.ddd.types.Entity<CalculatedNetIncome, SelfEmployedContributionId> {
    @EmbeddedId
    override val id: SelfEmployedContributionId = SelfEmployedContributionId(UUID.randomUUID())
}
