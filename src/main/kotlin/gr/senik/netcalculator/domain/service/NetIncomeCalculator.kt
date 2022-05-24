package gr.senik.netcalculator.domain.service

import gr.senik.common.domain.model.Money
import gr.senik.netcalculator.domain.model.income.Individual
import mu.KotlinLogging

private val log = KotlinLogging.logger {}

class NetIncomeCalculator(
    private val insuranceCost: Money,
    private val totalTax: Money,
    private val individual: Individual,
) {
    fun calculateNetIncome(): Money {
        log.info { "Insurance cost: $insuranceCost" }
        log.info { "Total tax: $totalTax" }
        return individual.grossIncome() - totalTax - insuranceCost
    }
}
