package gr.senik.tax.domain.service

import gr.senik.common.domain.model.Money
import gr.senik.tax.domain.model.IncomeTax
import gr.senik.tax.domain.model.SolidarityContributionTax
import gr.senik.tax.domain.model.selfemployedcontribution.SelfEmployedContributionTax

class TotalTaxCalculator(
    private val incomeTax: IncomeTax,
    private val solidarityContributionTax: SolidarityContributionTax,
    private val selfEmployedContributionTax: SelfEmployedContributionTax,
) {
    fun calculateTotalTax(): Money {
        return incomeTax.totalTaxAmount() +
                solidarityContributionTax.totalTaxAmount() +
                selfEmployedContributionTax.totalTaxAmount()
    }
}
