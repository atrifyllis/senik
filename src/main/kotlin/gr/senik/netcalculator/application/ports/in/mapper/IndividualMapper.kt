package gr.senik.netcalculator.application.ports.`in`.mapper

import gr.senik.netcalculator.application.ports.`in`.web.dto.IndividualDto
import gr.senik.netcalculator.domain.model.v2.*
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.ReportingPolicy

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
abstract class IndividualMapper {

    @Mapping(source = "id", target = "id")
    @Mapping(source = "individualDto.lessThanFiveYears", target = "isLessThanFiveYears")
    @Mapping(source = "individualDto.type", target = "insuranceType")
    @Mapping(source = "individualDto.annualExpensesAmount", target = "expensesAmount")
    @Mapping(source = "individualDto.legalEntityType", target = "type")
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
