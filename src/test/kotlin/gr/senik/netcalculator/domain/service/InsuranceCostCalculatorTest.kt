package gr.senik.netcalculator.domain.service

import gr.senik.common.domain.model.Money
import gr.senik.netcalculator.domain.model.insurance.InsuranceTestHelper
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class InsuranceCostCalculatorTest {
    @Test
    fun `should calculate total insurance cost`() {
        val insuredPerson = InsuranceTestHelper.insurancePersonWithAnnualIncome
        val efkaClasses = InsuranceTestHelper.efkaClasses
        val eteaepClasses = InsuranceTestHelper.eteaepClasses

        val totalInsuranceCost = InsuranceCostCalculator(insuredPerson, efkaClasses, eteaepClasses).calculateYearlyInsuranceCost()

        assertThat(totalInsuranceCost).isEqualTo(Money(288 * 12))
    }
}
