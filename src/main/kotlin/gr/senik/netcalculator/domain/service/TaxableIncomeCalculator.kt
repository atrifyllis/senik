package gr.senik.netcalculator.domain.service

import gr.senik.common.domain.model.Money
import gr.senik.netcalculator.domain.model.income.Individual

class TaxableIncomeCalculator(
    private val individual: Individual,
    private val insuranceCost: Money,
) {
    fun calculateTaxableIncome() = individual.grossIncome() - insuranceCost - individual.annualExpensesAmount
}
