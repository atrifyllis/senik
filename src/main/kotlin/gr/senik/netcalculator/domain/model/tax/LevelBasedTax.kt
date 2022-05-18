package gr.senik.netcalculator.domain.model.tax

import gr.senik.common.domain.model.Money
import mu.KotlinLogging

private val log = KotlinLogging.logger {}

/**
 * Represents a tax that is based on tax levels.
 */
abstract class LevelBasedTax(
    private val taxableIncome: Money,
    private val taxLevels: List<TaxLevel>,
) {
    // TODO this is not very readable
    /**
     * Starts from the taxableIncome and calculates for each level the tax amount that corresponds to the level.
     * For this, it uses the remainingIncome, which is the taxableIncome minus the amount that is taxed on that level (levelLimit).
     */
    fun totalTaxAmount(): Money {
        log.info { "taxableIncome: $taxableIncome" }
        val initial = AmountAccumulator(taxableIncome, Money.ZERO)
        return taxLevels.fold(initial) { (remainingIncome, totalAmount), level ->
            AmountAccumulator(
                remainingIncome - level.levelLimit,
                totalAmount + level.calculateLevelAmount(remainingIncome)
            )
        }.totalAmount
    }
}

data class AmountAccumulator(val remainingIncome: Money, val totalAmount: Money)
