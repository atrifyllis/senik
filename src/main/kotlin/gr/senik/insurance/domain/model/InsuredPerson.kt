package gr.senik.insurance.domain.model

import gr.senik.common.domain.model.Money

class InsuredPerson(
    val type: InsuranceType,
    val efkaClassId: EfkaClassId,
    val eteaepClassId: EteaepClassId,
    val grossAnnualIncome: Money?,
    val grossDailyIncome: Money?,
    val annualExpensesAmount: Money,
)
