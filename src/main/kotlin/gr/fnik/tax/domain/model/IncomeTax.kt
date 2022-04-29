package gr.fnik.tax.domain.model

import gr.fnik.common.domain.model.Money

class IncomeTax(
    taxableIncome: Money,
    private val taxLevels: List<IncomeTaxLevel>,
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
