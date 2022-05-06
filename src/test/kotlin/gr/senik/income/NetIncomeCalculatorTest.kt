package gr.senik.income

import gr.senik.common.domain.model.Money
import gr.senik.income.domain.model.Individual
import gr.senik.income.domain.service.NetIncomeCalculator
import gr.senik.insurance.domain.InsuranceTestHelper
import gr.senik.insurance.domain.model.InsuranceType
import gr.senik.insurance.domain.service.InsuranceCostCalculator
import gr.senik.tax.domain.model.IncomeTax
import gr.senik.tax.domain.model.SolidarityContributionTax
import gr.senik.tax.domain.model.TaxTestHelper
import gr.senik.tax.domain.model.selfemployedcontribution.SECType
import gr.senik.tax.domain.model.selfemployedcontribution.SelfEmployedContributionTax
import gr.senik.tax.domain.model.selfemployedcontribution.SelfEmployedContributionType
import gr.senik.tax.domain.service.TotalTaxCalculator
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
            annualExpensesAmount = Money.ZERO
        )

        val insuranceCostCalculator = InsuranceCostCalculator(individual, InsuranceTestHelper.efkaClasses, InsuranceTestHelper.eteaepClasses)
        val insuranceCost = insuranceCostCalculator.calculateYearlyInsuranceCost()
        val taxableIncome = individual.grossIncome() - insuranceCost - individual.annualExpensesAmount

        val incomeTax = IncomeTax(taxableIncome, TaxTestHelper.incomeTaxLevels())
        val solidarityContributionTax = SolidarityContributionTax(taxableIncome, TaxTestHelper.solidarityContributionTaxLevels())
        val selfEmployedContributionTax = SelfEmployedContributionTax(SelfEmployedContributionType(SECType.SINGLE_EMPLOYER_LARGE_AREA, Money(500)))

        val totalTaxCalculator = TotalTaxCalculator(
            incomeTax = incomeTax,
            solidarityContributionTax = solidarityContributionTax,
            selfEmployedContributionTax = selfEmployedContributionTax
        )

        val netIncomeCalculator = NetIncomeCalculator(
            insuranceCostCalculator = insuranceCostCalculator,
            totalTaxCalculator = totalTaxCalculator,
            individual = individual
        )

        val netIncome = netIncomeCalculator.calculateNetIncome()

        assertThat(netIncome).isEqualTo(Money(48574.68))
    }
}
