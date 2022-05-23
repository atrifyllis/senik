package gr.senik.netcalculator.domain.service

import gr.senik.common.domain.model.Money
import gr.senik.netcalculator.domain.model.insurance.InsuranceTestHelper
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class TaxableIncomeCalculatorTest {
    @Test
    fun `should calculate taxable income`() {
        val individual = InsuranceTestHelper.individualWithDailyIncomes
        val calculator = TaxableIncomeCalculator(individual, Money(1_000))
        val result = calculator.calculateTaxableIncome()

        assertThat(result).isEqualTo(Money(64_800))
    }
}
