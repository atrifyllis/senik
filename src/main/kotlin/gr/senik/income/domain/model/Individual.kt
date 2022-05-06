package gr.senik.income.domain.model

import gr.senik.common.domain.model.Money
import gr.senik.insurance.domain.model.EfkaClassId
import gr.senik.insurance.domain.model.EteaepClassId
import gr.senik.insurance.domain.model.InsuranceType


class Individual(
    val type: InsuranceType,
    val efkaClassId: EfkaClassId,
    val eteaepClassId: EteaepClassId,
    val grossAnnualIncome: Money?,
    val grossDailyIncomes: List<DailyIncome>,
    val annualExpensesAmount: Money,
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
