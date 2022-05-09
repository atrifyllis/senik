package gr.senik.netcalculator.domain.model.tax

import gr.senik.common.domain.model.AbstractAggregateRoot
import gr.senik.common.domain.model.DomainEntityId
import gr.senik.common.domain.model.Money
import java.util.*
import javax.persistence.*

@Embeddable
class TaxLevelId(var id: UUID?) : DomainEntityId(id)

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
abstract class TaxLevel(

    @Embedded
    @AttributeOverride(name = "amount", column = Column(name = "level_limit_amount"))
    internal val levelLimit: Money,

    private val levelFactor: Double,

    ) : AbstractAggregateRoot<TaxLevel, TaxLevelId>() {

    @EmbeddedId
    private val id: TaxLevelId = TaxLevelId(UUID.randomUUID())
    override fun getId(): TaxLevelId {
        return id
    }

    // TODO this is ugly
    fun calculateLevelAmount(remainingAmount: Money): Money {
        var taxAmount: Money = Money.ZERO
        if (remainingAmount < Money.ZERO) return taxAmount
        val taxableAmount: Money = calculateTaxableAmount(remainingAmount)
        taxAmount = taxableAmount * levelFactor
        return taxAmount
    }

    private fun calculateTaxableAmount(remainingAmount: Money) = if (remainingAmount <= levelLimit) remainingAmount else levelLimit
}
