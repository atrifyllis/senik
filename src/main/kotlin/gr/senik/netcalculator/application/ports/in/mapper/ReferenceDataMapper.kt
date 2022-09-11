package gr.senik.netcalculator.application.ports.`in`.mapper

import gr.senik.netcalculator.application.ports.`in`.web.dto.ReferenceDataDto
import gr.senik.netcalculator.domain.model.insurance.EfkaClass
import gr.senik.netcalculator.domain.model.insurance.EteaepClass
import gr.senik.netcalculator.domain.model.insurance.InsuranceType
import gr.senik.netcalculator.domain.model.tax.IncomeTaxLevel
import gr.senik.netcalculator.domain.model.tax.SolidarityContributionTaxLevel
import gr.senik.netcalculator.domain.model.tax.selfemployedcontribution.SelfEmployedContribution
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.ReportingPolicy

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE,
    uses = [InsuranceTypeMapper::class] // could not find a way to use the custom mapping without explicit mention here
)
abstract class ReferenceDataMapper {

    @Mapping(target = "efkaClasses", source = "efkaClasses")
    @Mapping(target = "eteaepClasses", source = "eteaepClasses")
    @Mapping(target = "incomeTaxLevels", source = "incomeTaxLevels")
    @Mapping(target = "solidarityContributionTaxLevels", source = "solidarityContributionTaxLevels")
    @Mapping(target = "selfEmployedContributions", source = "selfEmployedContributions")
    @Mapping(target = "enabledInsuranceTypes", source = "enabledInsuranceTypes")
    abstract fun toReferenceDataDto(
        dummy: Dummy,
        efkaClasses: List<EfkaClass>,
        eteaepClasses: List<EteaepClass>,
        incomeTaxLevels: List<IncomeTaxLevel>,
        solidarityContributionTaxLevels: List<SolidarityContributionTaxLevel>,
        selfEmployedContributions: List<SelfEmployedContribution>,
        enabledInsuranceTypes: List<InsuranceType>,
    ): ReferenceDataDto
}

/**
 * Dummy class to force the mapper to map iterables to non-iterable.
 */
class Dummy {

}
