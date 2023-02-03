package gr.senik.netcalculator.adapters.secondary.persistence.mapper

import gr.senik.netcalculator.adapters.secondary.persistence.SolidarityContributionLevelEntity
import gr.senik.netcalculator.domain.model.v2.TaxLevel
import gr.senik.netcalculator.domain.model.v2.TaxLevelId
import org.mapstruct.Mapper
import org.mapstruct.ReportingPolicy
import java.util.*

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
abstract class SolidarityContributionLevelEntityMapper {
    abstract fun toModel(solidarityContributionLevels: MutableList<SolidarityContributionLevelEntity>): List<TaxLevel>

    fun map(id: UUID): TaxLevelId {
        return TaxLevelId((id))
    }
}
