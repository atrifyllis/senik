package gr.senik.netcalculator.application.ports.`in`.mapper

import gr.senik.netcalculator.application.ports.`in`.web.dto.SelfEmployedContributionDto
import gr.senik.netcalculator.domain.model.v2.SelfEmployedContribution
import org.mapstruct.Mapper
import org.mapstruct.ReportingPolicy

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
abstract class SelfEmployedContributionMapper {

    abstract fun toSelfEmployedContributionDto(selfEmployedContribution: SelfEmployedContribution): SelfEmployedContributionDto

}
