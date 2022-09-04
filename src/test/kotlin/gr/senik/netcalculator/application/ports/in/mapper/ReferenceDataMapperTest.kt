package gr.senik.netcalculator.application.ports.`in`.mapper

import gr.senik.common.domain.model.Money
import gr.senik.netcalculator.domain.model.insurance.EfkaClassType
import gr.senik.netcalculator.domain.model.insurance.InsuranceTestHelper
import gr.senik.netcalculator.domain.model.insurance.InsuranceType
import gr.senik.netcalculator.domain.model.tax.TaxTestHelper
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class ReferenceDataMapperTest {

    @Test
    fun `should map reference data`() {
        val efkaClasses = InsuranceTestHelper.efkaClasses
        val eteaepClasses = InsuranceTestHelper.eteaepClasses
        val incomeTaxLevels = TaxTestHelper.incomeTaxLevels()
        val solidarityContributionTaxLevels = TaxTestHelper.solidarityContributionTaxLevels()
        val selfEmployedContributions = TaxTestHelper.selfEmployedContributions()
        val referenceData = ReferenceDataMapperImpl().toReferenceDataDto(
            Dummy(),
            efkaClasses,
            eteaepClasses,
            incomeTaxLevels,
            solidarityContributionTaxLevels,
            selfEmployedContributions,
            InsuranceType.values().toList()
        )

        assertThat(referenceData.efkaClasses[0].id).isEqualTo(InsuranceTestHelper.EFKA_CLASS_ID_1)
        assertThat(referenceData.efkaClasses[0].type).isEqualTo(EfkaClassType.FIRST)
        assertThat(referenceData.efkaClasses[0].mainPensionAmount).isEqualTo(Money(155))
        assertThat(referenceData.selfEmployedContributions[0].amount).isEqualTo(Money(500))
    }
}
