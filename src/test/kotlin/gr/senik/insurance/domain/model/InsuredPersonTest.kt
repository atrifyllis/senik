package gr.senik.insurance.domain.model

import gr.senik.TestHelper
import gr.senik.common.domain.model.Money
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class InsuredPersonTest {
    @Test
    fun `should calculate gross income from annual income`() {
        val insuredPerson = InsuredPerson(
            type = InsuranceType.TSMEDE,
            efkaClassId = TestHelper.EFKA_CLASS_ID_1,
            eteaepClassId = TestHelper.ETEAEP_CLASS_ID_1,
            grossAnnualIncome = Money(85_000),
            grossDailyIncomes = emptyList(),
            annualExpensesAmount = Money(0)
        )

        assertThat(insuredPerson.grossIncome()).isEqualTo(Money(85_000))
    }

    @Test
    fun `should calculate gross income from two daily incomes`() {
        val insuredPerson = InsuredPerson(
            type = InsuranceType.TSMEDE,
            efkaClassId = TestHelper.EFKA_CLASS_ID_1,
            eteaepClassId = TestHelper.ETEAEP_CLASS_ID_1,
            grossAnnualIncome = null,
            grossDailyIncomes = listOf(
                DailyIncome(120, Money(240)),
                DailyIncome(100, Money(370))
            ),
            annualExpensesAmount = Money(0)
        )

        assertThat(insuredPerson.grossIncome()).isEqualTo(Money(65800))
    }
}
