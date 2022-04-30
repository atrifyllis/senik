package gr.senik.tax.domain.model.selfemployedcontribution

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
}
