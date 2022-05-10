package gr.senik.netcalculator.application.ports.`in`.mapper

import gr.senik.netcalculator.application.ports.`in`.web.dto.EteaepClassDto
import gr.senik.netcalculator.domain.model.insurance.EteaepClass
import org.mapstruct.Mapper
import org.mapstruct.ReportingPolicy

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
abstract class EteaepClassMapper {
    abstract fun toEteaepClassDto(eteaepClass: EteaepClass): EteaepClassDto
}
