package gr.senik.netcalculator.application.ports.`in`.mapper

import gr.senik.netcalculator.application.ports.`in`.web.dto.CalculationResultDto
import gr.senik.netcalculator.application.service.NetAnnualIncome
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.ReportingPolicy

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
abstract class CalculationResultMapper {

    @Mapping(source = "netAnnualIncome", target = "netIncome")
    abstract fun toCalculationResult(result: NetAnnualIncome): CalculationResultDto
}
