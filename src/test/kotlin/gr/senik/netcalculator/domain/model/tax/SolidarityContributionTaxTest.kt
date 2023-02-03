package gr.senik.netcalculator.domain.model.tax

import gr.senik.common.domain.model.Money
import gr.senik.netcalculator.domain.model.v2.LegalEntityType
import gr.senik.netcalculator.domain.model.v2.SolidarityTax
import gr.senik.netcalculator.domain.model.v2.TaxDataHelper
import gr.senik.netcalculator.domain.model.v2.TaxLevel
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

internal class SolidarityContributionTaxTest {
    private lateinit var taxLevels: List<TaxLevel>

    @BeforeEach
    fun setUp() {
        taxLevels = TaxDataHelper.solidarityTaxLevels()
    }

    @Test
    fun `should calculate solidarity contribution tax without excess`() {
        val solidarityContributionTax = SolidarityTax(LegalEntityType.INDIVIDUAL, taxLevels)

        assertThat(solidarityContributionTax.calculateTax(Money(80_000))).isEqualTo(Money(4_551))

    }

    @Test
    fun `should calculate solidarity contribution tax with excess`() {
        val solidarityContributionTax = SolidarityTax(LegalEntityType.INDIVIDUAL, taxLevels)

        assertThat(solidarityContributionTax.calculateTax(Money(240_000))).isEqualTo(Money(19_151))

    }
}
