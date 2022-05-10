package gr.senik.netcalculator.application.service

import gr.senik.netcalculator.IntegrationTestBase
import gr.senik.netcalculator.domain.model.insurance.EfkaClassType
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

        assertThat(eteaepClasses[0].type).isEqualTo(EfkaClassType.FIRST)
    }
}
