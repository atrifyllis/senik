package gr.senik.tax.domain.model

import gr.senik.common.domain.model.Money

/**
 * Represents a tax that is based on tax levels.
 */
abstract class LevelBasedTax(
    taxableIncome: Money,
    private val taxLevels: List<TaxLevel>,
) {
    // TODO this is ugly
    init {
        var remainingIncome: Money = taxableIncome
        taxLevels.forEach {
            it.calculateLevelAmount(remainingIncome)
            remainingIncome -= it.levelLimit
        }
    }

    fun totalTaxAmount(): Money {
        return Money(taxLevels.map { it.taxAmount.amount }.sumOf { it })
    }
}
