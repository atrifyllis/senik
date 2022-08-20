package gr.senik.netcalculator.domain.model.income

import gr.senik.common.domain.model.Money
import gr.senik.netcalculator.domain.model.insurance.EfkaClassId
import gr.senik.netcalculator.domain.model.insurance.EteaepClassId
import gr.senik.netcalculator.domain.model.insurance.InsuranceType
import gr.senik.netcalculator.domain.model.tax.selfemployedcontribution.SECType


class Individual(
    val type: InsuranceType,
    val efkaClassId: EfkaClassId,
    val eteaepClassId: EteaepClassId,
    val grossAnnualIncome: Money?,
    val grossDailyIncomes: List<DailyIncome>,
    val annualExpensesAmount: Money,
    val isLessThanFiveYears: Boolean = false,
    val secType: SECType = SECType.SINGLE_EMPLOYER_LARGE_AREA,
    val branches: Int = 1,
) {

    val grossIncome: Money = grossAnnualIncome ?: sumGrossDailyAmounts()

    private fun sumGrossDailyAmounts() = Money(
        grossDailyIncomes
            .map { it.dailyIncome * it.days }
            .sumOf { it.amount }
    )
}

data class DailyIncome(val days: Int, val dailyIncome: Money)
