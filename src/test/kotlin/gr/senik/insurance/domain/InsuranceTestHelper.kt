package gr.senik.insurance.domain

import gr.senik.common.domain.model.Money
import gr.senik.income.domain.model.DailyIncome
import gr.senik.income.domain.model.Individual
import gr.senik.insurance.domain.model.*
import java.util.*

class InsuranceTestHelper {
    companion object {
        val EFKA_CLASS_ID_1 = EfkaClassId(UUID.fromString("d8f9f8f0-f8f8-11e9-b210-d663bd873d93"))
        val ETEAEP_CLASS_ID_1 = EteaepClassId(UUID.fromString("d8f9f8f1-f8f8-11e9-b210-d663bd873d93"))

        val efkaClasses = listOf(
            EfkaClass(
                id = EFKA_CLASS_ID_1,
                type = EfkaClassType.FIRST,
                mainPensionAmount = Money(155),
                healthCareMoneyAmount = Money(5),
                healthCareKindAmount = Money(50),
                unemploymentAmount = Money(10),
            )
        )
        val eteaepClasses = listOf(
            EteaepClass(
                id = ETEAEP_CLASS_ID_1,
                type = EteaepClassType.FIRST,
                auxiliaryPensionAmount = Money(42),
                lumpSumAmount = Money(26),
            )
        )

        val insurancePersonWithAnnualIncome = Individual(
            type = InsuranceType.TSMEDE,
            efkaClassId = EFKA_CLASS_ID_1,
            eteaepClassId = ETEAEP_CLASS_ID_1,
            grossAnnualIncome = Money(85_000),
            grossDailyIncomes = emptyList(),
            annualExpensesAmount = Money.ZERO
        )

        val individualWithDailyIncomes = Individual(
            type = InsuranceType.TSMEDE,
            efkaClassId = InsuranceTestHelper.EFKA_CLASS_ID_1,
            eteaepClassId = InsuranceTestHelper.ETEAEP_CLASS_ID_1,
            grossAnnualIncome = null,
            grossDailyIncomes = listOf(
                DailyIncome(120, Money(240)),
                DailyIncome(100, Money(370))
            ),
            annualExpensesAmount = Money.ZERO
        )
    }
}
