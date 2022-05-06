package gr.senik.tax.domain.model

import gr.senik.common.domain.model.Money

abstract class TaxLevel(
    internal val levelLimit: Money,
    private val levelFactor: Double,

    ) {

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
