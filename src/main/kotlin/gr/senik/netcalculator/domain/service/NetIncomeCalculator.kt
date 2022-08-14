package gr.senik.netcalculator.domain.service

import gr.senik.common.domain.model.Money
import gr.senik.netcalculator.domain.model.income.Individual
import gr.senik.netcalculator.domain.model.insurance.EfkaClass
import gr.senik.netcalculator.domain.model.insurance.EteaepClass
import gr.senik.netcalculator.domain.model.tax.IncomeTax
import gr.senik.netcalculator.domain.model.tax.IncomeTaxLevel
import gr.senik.netcalculator.domain.model.tax.SolidarityContributionTax
import gr.senik.netcalculator.domain.model.tax.SolidarityContributionTaxLevel
import gr.senik.netcalculator.domain.model.tax.selfemployedcontribution.SelfEmployedContribution
import gr.senik.netcalculator.domain.model.tax.selfemployedcontribution.SelfEmployedContributionTax
import mu.KotlinLogging

private val log = KotlinLogging.logger {}

class NetIncomeCalculator(
    private val individual: Individual,
    efkaClasses: List<EfkaClass>,
    eteaepClasses: List<EteaepClass>,
    incomeTaxLevels: List<IncomeTaxLevel>,
    solidarityContributionTaxLevels: List<SolidarityContributionTaxLevel>,
    selfEmployedContributions: List<SelfEmployedContribution>,
) {
    private val insuranceCostCalculator = InsuranceCostCalculator(
        individual = individual,
        efkaClasses = efkaClasses,
        eteaepClasses = eteaepClasses,
    )
    private val insuranceCost = insuranceCostCalculator.calculateYearlyInsuranceCost()

    private val taxableIncomeCalculator = TaxableIncomeCalculator(individual, insuranceCost)
    private val taxableIncome = taxableIncomeCalculator.calculateTaxableIncome()

    private val incomeTax = IncomeTax(
        taxableIncome = taxableIncome,
        taxLevels = incomeTaxLevels,
    )

    private val solidarityContributionTax = SolidarityContributionTax(
        taxableIncome = taxableIncome,
        taxLevels = solidarityContributionTaxLevels,
    )

    private val selfEmployedContributionTax = SelfEmployedContributionTax(
        type = individual.secType,
        branches = individual.branches,
        isLessThanFiveYears = individual.isLessThanFiveYears,
        selfEmployedContributions = selfEmployedContributions,
    )

    private val totalTaxCalculator = TotalTaxCalculator(
        incomeTax = incomeTax,
        solidarityContributionTax = solidarityContributionTax,
        selfEmployedContributionTax = selfEmployedContributionTax,
    )
    private val totalTax = totalTaxCalculator.calculateTotalTax()


    fun calculateNetIncome(): NetAnnualIncome {
        log.info { "Insurance cost: $insuranceCost" }
        log.info { "Total tax: $totalTax" }
        val netAnnualIncome = individual.grossIncome() - totalTax - insuranceCost
        return NetAnnualIncome(insuranceCost, totalTax, netAnnualIncome)
    }

    data class NetAnnualIncome(
        val insuranceCost: Money,
        val totalTax: Money,
        val netAnnualIncome: Money,
    )
}
