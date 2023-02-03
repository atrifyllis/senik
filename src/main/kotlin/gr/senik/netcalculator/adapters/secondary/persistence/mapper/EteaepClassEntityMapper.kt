package gr.senik.netcalculator.adapters.secondary.persistence.mapper

import gr.senik.netcalculator.adapters.secondary.persistence.EteaepClassEntity
import gr.senik.netcalculator.domain.model.v2.EteaepClass
import gr.senik.netcalculator.domain.model.v2.EteaepClassId
import org.mapstruct.Mapper
import org.mapstruct.ReportingPolicy
import java.util.*

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
abstract class EteaepClassEntityMapper {
    abstract fun toEteaepClassModel(eteaepClass: MutableList<EteaepClassEntity>): List<EteaepClass>

    fun map(id: UUID): EteaepClassId {
        return EteaepClassId((id))
    }
}
