package gr.senik.netcalculator.application.ports.`in`.mapper

import gr.senik.netcalculator.application.ports.`in`.web.dto.IndividualDto
import gr.senik.netcalculator.domain.model.income.Individual
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.ReportingPolicy

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
abstract class IndividualMapper {

    @Mapping(source = "efkaClassId", target = "efkaClassId.id")
    @Mapping(source = "eteaepClassId", target = "eteaepClassId.id")
    abstract fun toIndividual(individualDto: IndividualDto): Individual

}
