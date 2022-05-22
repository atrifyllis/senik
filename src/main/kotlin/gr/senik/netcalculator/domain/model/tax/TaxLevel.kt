package gr.senik.netcalculator.domain.model.tax

import gr.senik.common.domain.model.AbstractAggregateRoot
import gr.senik.common.domain.model.DomainEntityId
import gr.senik.common.domain.model.Money
import java.util.*
import javax.persistence.*

@Embeddable
class TaxLevelId(id: UUID?) : DomainEntityId(id)

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
abstract class TaxLevel(

    @Embedded
    @AttributeOverride(name = "amount", column = Column(name = "level_limit_amount"))
    @AttributeOverride(name = "currencyCode", column = Column(name = "currency_level_limit"))
    val levelLimit: Money,

    val levelFactor: Double,

    ) : AbstractAggregateRoot<TaxLevel, TaxLevelId>() {

    @EmbeddedId
    private val id: TaxLevelId = TaxLevelId(UUID.randomUUID())
    override fun getId(): TaxLevelId {
        return id
    }

    // TODO this is ugly
    fun calculateLevelAmount(remainingAmount: Money): Money {
        if (remainingAmount < Money.ZERO) return Money.ZERO
        val taxableAmount: Money = calculateTaxableAmount(remainingAmount)
        return taxableAmount * levelFactor
    }

    private fun calculateTaxableAmount(remainingAmount: Money) = if (remainingAmount <= levelLimit) remainingAmount else levelLimit
}
