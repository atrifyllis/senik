package gr.senik.netcalculator.domain.service

import gr.senik.common.domain.model.Money
import gr.senik.netcalculator.domain.model.tax.IncomeTax
import gr.senik.netcalculator.domain.model.tax.SolidarityContributionTax
import gr.senik.netcalculator.domain.model.tax.selfemployedcontribution.SelfEmployedContributionTax
import mu.KotlinLogging

private val log = KotlinLogging.logger {}

class TotalTaxCalculator(
    private val incomeTax: IncomeTax,
    private val solidarityContributionTax: SolidarityContributionTax,
    private val selfEmployedContributionTax: SelfEmployedContributionTax,
) {
    fun calculateTotalTax(): Money {
        val incomeTaxAmount = incomeTax.totalTaxAmount
        val solidarityContributionTaxAmount = solidarityContributionTax.totalTaxAmount
        val selfEmployedContributionTaxAmount = selfEmployedContributionTax.totalTaxAmount
        log.info { "income Tax: $incomeTaxAmount" }
        log.info { "solidarity contribution tax: $solidarityContributionTaxAmount" }
        log.info { "self employed contribution Tax: $selfEmployedContributionTaxAmount" }
        return incomeTaxAmount +
                solidarityContributionTaxAmount +
                selfEmployedContributionTaxAmount
    }
}
