package gr.senik.netcalculator.application.ports.`in`.mapper

import gr.senik.netcalculator.application.ports.`in`.dto.CalculationResultDto
import gr.senik.netcalculator.domain.model.Income
import org.mapstruct.Mapper
import org.mapstruct.ReportingPolicy

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
abstract class CalculationResultMapper {
    abstract fun toCalculationResult(result: Income): CalculationResultDto
}
