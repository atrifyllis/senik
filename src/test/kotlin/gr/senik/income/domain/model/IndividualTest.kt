package gr.senik.income.domain.model

import gr.senik.common.domain.model.Money
import gr.senik.insurance.domain.InsuranceTestHelper
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class IndividualTest {
    @Test
    fun `should calculate gross income from annual income`() {
        val insuredPerson = InsuranceTestHelper.insurancePersonWithAnnualIncome

        assertThat(insuredPerson.grossIncome()).isEqualTo(Money(85_000))
    }

    @Test
    fun `should calculate gross income from two daily incomes`() {
        val insuredPerson = InsuranceTestHelper.individualWithDailyIncomes

        assertThat(insuredPerson.grossIncome()).isEqualTo(Money(65800))
    }
}
