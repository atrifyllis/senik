package gr.senik.netcalculator.domain.service

import gr.senik.common.domain.model.Money
import gr.senik.netcalculator.domain.model.income.Individual
import mu.KotlinLogging

private val log = KotlinLogging.logger {}

class NetIncomeCalculator(
    private val insuranceCostCalculator: InsuranceCostCalculator,
    private val totalTaxCalculator: TotalTaxCalculator,
    private val individual: Individual
) {
    fun calculateNetIncome(): Money {
        val insuranceCost = insuranceCostCalculator.calculateYearlyInsuranceCost()
        log.info { "Insurance cost: $insuranceCost" }
        val totalTax = totalTaxCalculator.calculateTotalTax()
        log.info { "Total tax: $totalTax" }
        return individual.grossIncome() - totalTax - insuranceCost
    }
}
