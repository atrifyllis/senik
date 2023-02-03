package gr.senik.netcalculator.application.ports.`in`.mapper

import gr.senik.netcalculator.application.ports.`in`.web.dto.ReferenceDataDto
import gr.senik.netcalculator.domain.model.v2.*
import org.mapstruct.InjectionStrategy
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.ReportingPolicy

@Mapper(
    componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE,
    injectionStrategy = InjectionStrategy.CONSTRUCTOR, // otherwise field injection is used for mapper below which is bad practice
    uses = [InsuranceTypeMapper::class,
        SelfEmployedContributionMapper::class
    ] // could not find a way to use the custom mapping without explicit mention here,
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
        incomeTaxLevels: List<TaxLevel>,
        solidarityContributionTaxLevels: List<TaxLevel>,
        selfEmployedContributions: List<SelfEmployedContribution>,
        enabledInsuranceTypes: List<InsuranceType>,
    ): ReferenceDataDto
}

