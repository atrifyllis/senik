package gr.senik.netcalculator.adapters.secondary.persistence.mapper

import gr.senik.netcalculator.adapters.secondary.persistence.SelfEmployedContributionEntity
import gr.senik.netcalculator.domain.model.v2.SelfEmployedContribution
import gr.senik.netcalculator.domain.model.v2.SelfEmployedContributionId
import org.mapstruct.Mapper
import org.mapstruct.ReportingPolicy
import java.util.*

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
abstract class SelfEmployedContributionEntityMapper {
    abstract fun toModel(selfEmployedContributions: SelfEmployedContributionEntity): SelfEmployedContribution
    abstract fun toModel(selfEmployedContributions: MutableList<SelfEmployedContributionEntity>): List<SelfEmployedContribution>

    fun map(id: UUID): SelfEmployedContributionId {
        return SelfEmployedContributionId((id))
    }
}
