package gr.senik.netcalculator.domain.model.tax.selfemployedcontribution

import gr.senik.common.domain.model.Money
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class SelfEmployedContributionTaxTest {
    @Test
    fun `should calculate self-employed contribution tax`() {
        val selfEmployedContributionTax = SelfEmployedContributionTax(
            SelfEmployedContributionType(SECType.SINGLE_EMPLOYER_LARGE_AREA, Money(500))
        )

        assertThat(selfEmployedContributionTax.totalTax).isEqualTo(Money(500))
    }

    @Test
    fun `should calculate self-employed contribution tax when insured less than 5 years`() {
        val selfEmployedContributionTax = SelfEmployedContributionTax(
            SelfEmployedContributionType(SECType.SINGLE_EMPLOYER_LARGE_AREA, Money(500)),
            isLessThanFiveYears = true
        )

        assertThat(selfEmployedContributionTax.totalTax).isEqualTo(Money(0))
    }
}
