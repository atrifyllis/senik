package gr.fnik.tax.domain.model

import gr.fnik.common.domain.model.Money
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

internal class SolidarityContributionTaxTest {
    private lateinit var taxLevels: List<SolidarityContributionTaxLevel>

    @BeforeEach
    fun setUp() {
        taxLevels = listOf(
            SolidarityContributionTaxLevel(SolidarityContributionLevelType.FIRST_12K, Money(12_000), 0.0),
            SolidarityContributionTaxLevel(SolidarityContributionLevelType.SECOND_8K, Money(8_000), 0.022),
            SolidarityContributionTaxLevel(SolidarityContributionLevelType.THIRD_10K, Money(10_000), 0.05),
            SolidarityContributionTaxLevel(SolidarityContributionLevelType.FOURTH_10K, Money(10_000), 0.065),
            SolidarityContributionTaxLevel(SolidarityContributionLevelType.FIFTH_25K, Money(25_000), 0.075),
            SolidarityContributionTaxLevel(SolidarityContributionLevelType.SIXTH_155K, Money(155_000), 0.09),
            SolidarityContributionTaxLevel(SolidarityContributionLevelType.EXCESS, Money(Integer.MAX_VALUE), 0.1),
        )
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
