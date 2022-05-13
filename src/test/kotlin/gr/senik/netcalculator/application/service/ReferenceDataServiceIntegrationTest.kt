package gr.senik.netcalculator.application.service

import gr.senik.common.domain.model.Money
import gr.senik.netcalculator.IntegrationTestBase
import gr.senik.netcalculator.domain.model.insurance.EfkaClassType
import gr.senik.netcalculator.domain.model.insurance.EteaepClassType
import gr.senik.netcalculator.domain.model.tax.SolidarityContributionLevelType
import gr.senik.netcalculator.domain.model.tax.TaxLevelType
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired

internal class ReferenceDataServiceIntegrationTest : IntegrationTestBase() {

    @Autowired
    lateinit var referenceDataService: ReferenceDataService

    @Test
    fun `should return reference data`() {

        val (eteaepClasses, efkaClasses, incomeTaxLevels, solidarityContributionTaxLevels) =
            referenceDataService.getReferenceData()

        assertThat(eteaepClasses[0].type).isEqualTo(EteaepClassType.FIRST)
        assertThat(eteaepClasses[0].lumpSumAmount).isEqualTo(Money(26))

        assertThat(efkaClasses[1].type).isEqualTo(EfkaClassType.SECOND)
        assertThat(efkaClasses[1].mainPensionAmount).isEqualTo(Money(186))

        assertThat(incomeTaxLevels[2].type).isEqualTo(TaxLevelType.THIRD_10K)
        assertThat(incomeTaxLevels[2].levelFactor).isEqualTo(28.0)

        assertThat(solidarityContributionTaxLevels[4].type).isEqualTo(SolidarityContributionLevelType.FIFTH_25K)
        assertThat(solidarityContributionTaxLevels[4].levelLimit).isEqualTo(Money(25_000))
    }
}
