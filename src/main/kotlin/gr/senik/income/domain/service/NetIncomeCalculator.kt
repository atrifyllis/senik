package gr.senik.income.domain.service

import gr.senik.common.domain.model.Money
import gr.senik.insurance.domain.model.InsuredPerson
import gr.senik.insurance.domain.service.InsuranceCostCalculator
import gr.senik.tax.domain.service.TotalTaxCalculator
import mu.KotlinLogging

private val log = KotlinLogging.logger {}

class NetIncomeCalculator(
    private val insuranceCostCalculator: InsuranceCostCalculator,
    private val totalTaxCalculator: TotalTaxCalculator,
    private val insuredPerson: InsuredPerson
) {
    fun calculateNetIncome(): Money {
        val insuranceCost = insuranceCostCalculator.calculateYearlyInsuranceCost()
        log.info { "Insurance cost: $insuranceCost" }
        val totalTax = totalTaxCalculator.calculateTotalTax()
        log.info { "Total tax: $totalTax" }
        return insuredPerson.grossIncome() - totalTax - insuranceCost
    }
}
