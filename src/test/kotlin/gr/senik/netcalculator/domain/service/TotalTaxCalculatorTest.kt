package gr.senik.netcalculator.domain.service

import gr.senik.common.domain.model.Money
import gr.senik.netcalculator.domain.model.tax.IncomeTax
import gr.senik.netcalculator.domain.model.tax.SolidarityContributionTax
import gr.senik.netcalculator.domain.model.tax.TaxTestHelper
import gr.senik.netcalculator.domain.model.tax.selfemployedcontribution.SECType
import gr.senik.netcalculator.domain.model.tax.selfemployedcontribution.SelfEmployedContributionTax
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class TotalTaxCalculatorTest {
    @Test
    fun `should calculate total tax`() {
        val taxableIncome = Money(80_000)
        val incomeTaxLevels = TaxTestHelper.incomeTaxLevels()
        val solidarityContributionTaxLevels = TaxTestHelper.solidarityContributionTaxLevels()

        val incomeTax = IncomeTax(taxableIncome, incomeTaxLevels)
        val solidarityContributionTax = SolidarityContributionTax(taxableIncome, solidarityContributionTaxLevels)
        val selfEmployedContributionTax = SelfEmployedContributionTax(
            type = SECType.SINGLE_EMPLOYER_LARGE_AREA,
            selfEmployedContributions = TaxTestHelper.selfEmployedContributions()
        )
        val totalTaxCalculator = TotalTaxCalculator(
            incomeTax = incomeTax,
            solidarityContributionTax = solidarityContributionTax,
            selfEmployedContributionTax = selfEmployedContributionTax
        )

        assertThat(totalTaxCalculator.calculateTotalTax()).isEqualTo(Money(32151))
    }
}
