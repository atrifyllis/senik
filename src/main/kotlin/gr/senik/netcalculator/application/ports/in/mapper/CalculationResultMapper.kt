package gr.senik.netcalculator.application.ports.`in`.mapper

import gr.senik.netcalculator.application.ports.`in`.web.dto.CalculationResultDto
import gr.senik.netcalculator.domain.model.v2.Income
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.ReportingPolicy

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
abstract class CalculationResultMapper {

    @Mapping(source = "totalTaxAmount", target = "totalTax")
    @Mapping(source = "solidarityContributionAmount", target = "solidarityContributionTax")
    @Mapping(source = "selfEmployedContributionAmount", target = "selfEmployedContributionTax")
    abstract fun toCalculationResult(result: Income): CalculationResultDto
}
