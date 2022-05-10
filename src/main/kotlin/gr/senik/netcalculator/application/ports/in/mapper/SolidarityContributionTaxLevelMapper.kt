package gr.senik.netcalculator.application.ports.`in`.mapper

import gr.senik.netcalculator.application.ports.`in`.web.dto.SolidarityContributionTaxLevelDto
import gr.senik.netcalculator.domain.model.tax.SolidarityContributionTaxLevel
import org.mapstruct.Mapper
import org.mapstruct.ReportingPolicy

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
abstract class SolidarityContributionTaxLevelMapper {
    abstract fun toSolidarityContributionTaxLevelDto(solidarityContributionTaxLevel: SolidarityContributionTaxLevel): SolidarityContributionTaxLevelDto
}
