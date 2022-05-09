package gr.senik.netcalculator.domain.model.tax

import gr.senik.common.domain.model.Money
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

internal class SolidarityContributionTaxTest {
    private lateinit var taxLevels: List<SolidarityContributionTaxLevel>

    @BeforeEach
    fun setUp() {
        taxLevels = TaxTestHelper.solidarityContributionTaxLevels()
    }

    @Test
    fun `should calculate solidarity contribution tax without excess`() {
        val solidarityContributionTax = SolidarityContributionTax(Money(80_000), taxLevels)

        assertThat(solidarityContributionTax.totalTaxAmount()).isEqualTo(Money(4_551))

    }

    @Test
    fun `should calculate solidarity contribution tax with excess`() {
        val solidarityContributionTax = SolidarityContributionTax(Money(240_000), taxLevels)

        assertThat(solidarityContributionTax.totalTaxAmount()).isEqualTo(Money(19_151))

    }
}
