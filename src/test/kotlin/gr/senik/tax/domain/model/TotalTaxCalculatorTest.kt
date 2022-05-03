package gr.senik.tax.domain.model

import gr.senik.common.domain.model.Money
import gr.senik.tax.domain.model.selfemployedcontribution.SECType
import gr.senik.tax.domain.model.selfemployedcontribution.SelfEmployedContributionTax
import gr.senik.tax.domain.model.selfemployedcontribution.SelfEmployedContributionType
import gr.senik.tax.domain.service.TotalTaxCalculator
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
        val selfEmployedContributionTax = SelfEmployedContributionTax(SelfEmployedContributionType(SECType.SINGLE_EMPLOYER_LARGE_AREA, Money(500)))
        val totalTaxCalculator = TotalTaxCalculator(
            incomeTax = incomeTax,
            solidarityContributionTax = solidarityContributionTax,
            selfEmployedContributionTax = selfEmployedContributionTax
        )

        assertThat(totalTaxCalculator.calculateTotalTax()).isEqualTo(Money(32151))
    }
}
