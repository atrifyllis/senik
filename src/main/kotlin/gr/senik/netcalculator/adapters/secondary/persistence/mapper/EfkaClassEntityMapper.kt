package gr.senik.netcalculator.adapters.secondary.persistence.mapper

import gr.senik.netcalculator.adapters.secondary.persistence.EfkaClassEntity
import gr.senik.netcalculator.domain.model.v2.EfkaClass
import gr.senik.netcalculator.domain.model.v2.EfkaClassId
import org.mapstruct.Mapper
import org.mapstruct.ReportingPolicy
import java.util.*

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
abstract class EfkaClassEntityMapper {
    abstract fun toEfkaClassModel(efkaClass: MutableList<EfkaClassEntity>): List<EfkaClass>

    fun map(id: UUID): EfkaClassId {
        return EfkaClassId((id))
    }
}
