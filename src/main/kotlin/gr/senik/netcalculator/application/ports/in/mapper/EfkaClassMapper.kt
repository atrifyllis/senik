package gr.senik.netcalculator.application.ports.`in`.mapper

import gr.senik.netcalculator.application.ports.`in`.web.dto.EfkaClassDto
import gr.senik.netcalculator.domain.model.insurance.EfkaClass
import org.mapstruct.Mapper
import org.mapstruct.ReportingPolicy

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
abstract class EfkaClassMapper {
    abstract fun toEfkaClassDto(efkaClass: EfkaClass): EfkaClassDto
}
