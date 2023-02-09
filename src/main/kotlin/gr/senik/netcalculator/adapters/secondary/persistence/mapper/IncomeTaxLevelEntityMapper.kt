package gr.senik.netcalculator.adapters.secondary.persistence.mapper

import gr.senik.netcalculator.adapters.secondary.persistence.IncomeTaxLevelEntity
import gr.senik.netcalculator.domain.model.TaxLevel
import gr.senik.netcalculator.domain.model.TaxLevelId
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.ReportingPolicy
import java.util.*

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
abstract class IncomeTaxLevelEntityMapper {
    @Mapping(source = "incomeTaxLevels", target = "taxLevels")
    abstract fun toIncomeTaxLevelModel(incomeTaxLevels: MutableList<IncomeTaxLevelEntity>): List<TaxLevel>

    fun map(id: UUID): TaxLevelId {
        return TaxLevelId((id))
    }
}
