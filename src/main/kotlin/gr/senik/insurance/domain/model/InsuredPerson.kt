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
    // TODO not great
    fun grossIncome(): Money {
        return grossAnnualIncome ?: (grossDailyIncomes.map { it.income * it.days }.reduce { acc, m -> m + acc })
    }
}

data class DailyIncome(val days: Int, val income: Money)
