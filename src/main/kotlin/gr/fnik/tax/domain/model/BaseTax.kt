package gr.fnik.tax.domain.model

import gr.fnik.common.domain.model.Money

abstract class BaseTax(
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
