package gr.senik.netcalculator.domain.model.tax.selfemployedcontribution

import gr.senik.common.domain.model.Money
import gr.senik.netcalculator.domain.model.tax.TaxTestHelper
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

internal class SelfEmployedContributionTaxTest {

    private lateinit var selfEmployedContributions: List<SelfEmployedContribution>

    @BeforeEach
    fun setUp() {
        selfEmployedContributions = TaxTestHelper.selfEmployedContributions()
    }

    @Test
    fun `should calculate self-employed contribution tax`() {
        val selfEmployedContributionTax = SelfEmployedContributionTax(
            type = SECType.SINGLE_EMPLOYER_LARGE_AREA,
            selfEmployedContributions = selfEmployedContributions
        )

        assertThat(selfEmployedContributionTax.totalTax).isEqualTo(Money(500))
    }

    @Test
    fun `should calculate self-employed contribution tax when insured less than 5 years`() {
        val selfEmployedContributionTax = SelfEmployedContributionTax(
            type = SECType.SINGLE_EMPLOYER_LARGE_AREA,
            isLessThanFiveYears = true,
            selfEmployedContributions = selfEmployedContributions
        )

        assertThat(selfEmployedContributionTax.totalTax).isEqualTo(Money(0))
    }
}
