package gr.senik.netcalculator.domain.service

import gr.senik.common.domain.model.Money
import gr.senik.netcalculator.domain.model.income.Individual
import gr.senik.netcalculator.domain.model.insurance.InsuranceTestHelper
import gr.senik.netcalculator.domain.model.insurance.InsuranceType
import gr.senik.netcalculator.domain.model.tax.TaxTestHelper
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class NetIncomeCalculatorTest {
    @Test
    fun `should calculate net income`() {

        val individual = Individual(
            type = InsuranceType.TSMEDE,
            efkaClassId = InsuranceTestHelper.EFKA_CLASS_ID_1,
            eteaepClassId = InsuranceTestHelper.ETEAEP_CLASS_ID_1,
            grossAnnualIncome = Money(85_000),
            grossDailyIncomes = emptyList(),
            annualExpensesAmount = Money.ZERO,
        )

        val netIncomeCalculator = NetIncomeCalculator(
            individual = individual,
            efkaClasses = InsuranceTestHelper.efkaClasses,
            eteaepClasses = InsuranceTestHelper.eteaepClasses,
            incomeTaxLevels = TaxTestHelper.incomeTaxLevels(),
            solidarityContributionTaxLevels = TaxTestHelper.solidarityContributionTaxLevels(),
            selfEmployedContributions = TaxTestHelper.selfEmployedContributions()
        )

        val netIncome = netIncomeCalculator.calculateNetIncome()

        assertThat(netIncome.netAnnualIncome).isEqualTo(Money(48574.68))
    }

    @Test
    fun `should calculate net income when insured less than five years`() {

        val individual = Individual(
            type = InsuranceType.TSMEDE,
            efkaClassId = InsuranceTestHelper.EFKA_CLASS_ID_1,
            eteaepClassId = InsuranceTestHelper.ETEAEP_CLASS_ID_1,
            grossAnnualIncome = Money(85_000),
            grossDailyIncomes = emptyList(),
            annualExpensesAmount = Money.ZERO,
            isLessThanFiveYears = true
        )

        val netIncomeCalculator = NetIncomeCalculator(
            individual = individual,
            efkaClasses = InsuranceTestHelper.efkaClasses,
            eteaepClasses = InsuranceTestHelper.eteaepClasses,
            incomeTaxLevels = TaxTestHelper.incomeTaxLevels(),
            solidarityContributionTaxLevels = TaxTestHelper.solidarityContributionTaxLevels(),
            selfEmployedContributions = TaxTestHelper.selfEmployedContributions()
        )

        val netIncome = netIncomeCalculator.calculateNetIncome()

        assertThat(netIncome.netAnnualIncome).isEqualTo(Money(49074.68))
    }
}
