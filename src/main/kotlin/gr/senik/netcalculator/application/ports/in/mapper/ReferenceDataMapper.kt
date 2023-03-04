package gr.senik.netcalculator.application.ports.`in`.mapper

import gr.senik.netcalculator.application.ports.`in`.dto.ReferenceDataDto
import gr.senik.netcalculator.domain.model.*
import org.mapstruct.InjectionStrategy
import org.mapstruct.Mapper
import org.mapstruct.ReportingPolicy

@Mapper(
    componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE,
    injectionStrategy = InjectionStrategy.CONSTRUCTOR, // otherwise field injection is used for mapper below which is bad practice
    uses = [InsuranceTypeMapper::class] // cannot use the custom mapping without explicit mention here
)
abstract class ReferenceDataMapper {

    abstract fun toReferenceDataDto(
        efkaClasses: List<EfkaClass>,
        eteaepClasses: List<EteaepClass>,
        incomeTaxLevels: List<TaxLevel>,
        solidarityContributionTaxLevels: List<TaxLevel>,
        selfEmployedContributions: List<SelfEmployedContribution>,
        enabledInsuranceTypes: List<InsuranceType>,
    ): ReferenceDataDto
}

