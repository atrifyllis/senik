package gr.senik.netcalculator.domain.service

import gr.senik.common.domain.model.Money
import gr.senik.netcalculator.domain.model.tax.IncomeTax
import gr.senik.netcalculator.domain.model.tax.SolidarityContributionTax
import gr.senik.netcalculator.domain.model.tax.selfemployedcontribution.SelfEmployedContributionTax

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
