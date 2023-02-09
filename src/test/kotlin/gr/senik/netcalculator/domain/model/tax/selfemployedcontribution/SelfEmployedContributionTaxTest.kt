package gr.senik.netcalculator.domain.model.tax.selfemployedcontribution

import gr.senik.common.domain.model.Money
import gr.senik.netcalculator.domain.model.SECType
import gr.senik.netcalculator.domain.model.SelfEmployedContribution
import gr.senik.netcalculator.domain.model.SelfEmployedContributionId
import gr.senik.netcalculator.domain.model.tax.TaxTestHelper
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.util.*

internal class SelfEmployedContributionTaxTest {

    private lateinit var selfEmployedContributions: List<SelfEmployedContribution>

    @BeforeEach
    fun setUp() {
        selfEmployedContributions = TaxTestHelper.selfEmployedContributions()
    }

    @Test
    fun `should calculate self-employed contribution tax`() {
        val selfEmployedContributionTax = SelfEmployedContribution(
            id = SelfEmployedContributionId(UUID.randomUUID()),
            secType = SECType.SINGLE_EMPLOYER_LARGE_AREA,
            amount = Money(500)
        )

        assertThat(selfEmployedContributionTax.calculateTax(false, 1)).isEqualTo(Money(500))
    }

    @Test
    fun `should calculate self-employed contribution tax when insured less than 5 years`() {
        val selfEmployedContributionTax = SelfEmployedContribution(
            id = SelfEmployedContributionId(UUID.randomUUID()),
            secType = SECType.SINGLE_EMPLOYER_LARGE_AREA,
            amount = Money(500)
        )

        assertThat(selfEmployedContributionTax.calculateTax(true, 1)).isEqualTo(Money(0))
    }
}
