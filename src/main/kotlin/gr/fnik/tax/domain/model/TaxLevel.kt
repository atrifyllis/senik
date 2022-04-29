package gr.fnik.tax.domain.model

import gr.fnik.common.domain.model.Money

abstract class TaxLevel(
    internal val levelLimit: Money,
    private val levelFactor: Double,

    ) {
    internal var taxAmount: Money = Money(0)

    // TODO this is ugly
    fun calculateLevelAmount(remainingAmount: Money): Money {
        if (remainingAmount < Money(0)) return taxAmount
        val taxableAmount: Money = if (remainingAmount <= levelLimit) remainingAmount else levelLimit
        taxAmount = taxableAmount * levelFactor
        return taxAmount
    }
}
