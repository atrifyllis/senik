package gr.senik.tax.domain.model

import gr.senik.common.domain.model.Money
import gr.senik.tax.domain.model.selfemployedcontribution.SECType
import gr.senik.tax.domain.model.selfemployedcontribution.SelfEmployedContributionTax
import gr.senik.tax.domain.model.selfemployedcontribution.SelfEmployedContributionType
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class TotalTaxTest {
    @Test
    fun `should calculate total tax`() {
        val taxableIncome = Money(80_000)
        val incomeTaxLevels = TaxLevelHelper.incomeTaxLevels()
        val solidarityContributionTaxLevels = TaxLevelHelper.solidarityContributionTaxLevels()

        val incomeTax = IncomeTax(taxableIncome, incomeTaxLevels)
        val solidarityContributionTax = SolidarityContributionTax(taxableIncome, solidarityContributionTaxLevels)
        val selfEmployedContributionTax = SelfEmployedContributionTax(SelfEmployedContributionType(SECType.SINGLE_EMPLOYER_LARGE_AREA, Money(500)))
        val totalTax = TotalTax(
            incomeTax = incomeTax,
            solidarityContributionTax = solidarityContributionTax,
            selfEmployedContributionTax = selfEmployedContributionTax
        )

        assertThat(totalTax.calculateTotalTax()).isEqualTo(Money(32151))
    }
}
