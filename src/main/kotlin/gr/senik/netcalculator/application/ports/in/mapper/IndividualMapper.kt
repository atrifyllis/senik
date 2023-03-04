package gr.senik.netcalculator.application.ports.`in`.mapper

import gr.senik.netcalculator.application.ports.`in`.dto.IndividualDto
import gr.senik.netcalculator.domain.model.*
import org.mapstruct.InjectionStrategy
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.ReportingPolicy

@Mapper(
    componentModel = "spring",
    unmappedTargetPolicy = ReportingPolicy.IGNORE,
    injectionStrategy = InjectionStrategy.CONSTRUCTOR
)
abstract class IndividualMapper {

    @Mapping(source = "id", target = "id")
    @Mapping(source = "individualDto", target = ".")
    @Mapping(source = "individualDto.lessThanFiveYears", target = "isLessThanFiveYears")
    @Mapping(target = "legalEntityType", ignore = true)
    abstract fun toIndividual(
        id: LegalEntityId,
        individualDto: IndividualDto,
        efkaClass: EfkaClass,
        eteaepClass: EteaepClass,
        selfEmployedContribution: SelfEmployedContribution,
        incomeTax: IncomeTax,
        solidarityTax: SolidarityTax,
    ): Individual
}
