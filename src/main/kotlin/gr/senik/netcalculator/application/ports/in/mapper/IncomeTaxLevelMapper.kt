package gr.senik.netcalculator.application.ports.`in`.mapper

import gr.senik.netcalculator.application.ports.`in`.web.dto.IncomeTaxLevelDto
import gr.senik.netcalculator.domain.model.tax.IncomeTaxLevel
import org.mapstruct.Mapper
import org.mapstruct.ReportingPolicy

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
abstract class IncomeTaxLevelMapper {
    abstract fun toIncomeTaxLevelDto(incomeTaxLevel: IncomeTaxLevel): IncomeTaxLevelDto
}
