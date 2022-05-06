package gr.senik.insurance.domain.model

import gr.senik.common.domain.model.Money


class InsuredPerson(
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
