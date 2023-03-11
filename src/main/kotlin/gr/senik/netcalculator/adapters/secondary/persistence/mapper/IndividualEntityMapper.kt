package gr.senik.netcalculator.adapters.secondary.persistence.mapper

import gr.senik.netcalculator.adapters.secondary.persistence.IndividualEntity
import gr.senik.netcalculator.domain.model.EfkaClass
import gr.senik.netcalculator.domain.model.EteaepClass
import gr.senik.netcalculator.domain.model.Individual
import gr.senik.netcalculator.domain.model.LegalEntityId
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.ReportingPolicy
import java.util.*

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
abstract class IndividualEntityMapper {
    @Mapping(source = "individual.lessThanFiveYears", target = "lessThanFiveYears")
    @Mapping(source = "individual.efkaClass", target = "efkaClassId")
    @Mapping(source = "individual.eteaepClass", target = "eteaepClassId")
    @Mapping(source = "individual.selfEmployedContribution.secType", target = "secType")
    abstract fun toEntity(individual: Individual): IndividualEntity


    fun map(id: LegalEntityId): UUID {
        return id.id
    }

    fun map(id: EfkaClass): UUID {
        return id.id.id
    }

    fun map(id: EteaepClass): UUID {
        return id.id.id
    }


}
