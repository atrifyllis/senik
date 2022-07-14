package gr.senik.netcalculator.domain.model.income

import gr.senik.common.domain.model.Money
import gr.senik.netcalculator.domain.model.insurance.EfkaClassId
import gr.senik.netcalculator.domain.model.insurance.EteaepClassId
import gr.senik.netcalculator.domain.model.insurance.InsuranceType


class Individual(
    val type: InsuranceType,
    val efkaClassId: EfkaClassId,
    val eteaepClassId: EteaepClassId,
    val grossAnnualIncome: Money?,
    val grossDailyIncomes: List<DailyIncome>,
    val annualExpensesAmount: Money,
    val isLessThanFiveYears: Boolean = false,
) {

    fun grossIncome(): Money {
        return grossAnnualIncome ?: sumGrossDailyAmounts()
    }

    private fun sumGrossDailyAmounts() = Money(
        grossDailyIncomes
            .map { it.dailyIncome * it.days }
            .sumOf { it.amount }
    )
}

data class DailyIncome(val days: Int, val dailyIncome: Money)
