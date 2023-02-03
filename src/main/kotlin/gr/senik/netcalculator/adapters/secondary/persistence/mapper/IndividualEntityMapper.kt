package gr.senik.netcalculator.adapters.secondary.persistence.mapper

import gr.senik.netcalculator.adapters.secondary.persistence.IndividualEntity
import gr.senik.netcalculator.domain.model.v2.EfkaClass
import gr.senik.netcalculator.domain.model.v2.EteaepClass
import gr.senik.netcalculator.domain.model.v2.Individual
import gr.senik.netcalculator.domain.model.v2.LegalEntityId
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.ReportingPolicy
import java.util.*

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
abstract class IndividualEntityMapper {
    @Mapping(source = "individual.id", target = "id")
    @Mapping(source = "individual.insuranceType", target = "type")
    @Mapping(source = "individual.efkaClass", target = "efkaClassId")
    @Mapping(source = "individual.eteaepClass", target = "eteaepClassId")
//    @Mapping(source = "individual.id", target = "individual.id")
//    @Mapping(source = "individual.grossDailyIncomes", target = "individual.grossDailyIncomes")
    @Mapping(source = "individual.expensesAmount", target = "annualExpensesAmount")
    @Mapping(source = "individual.selfEmployedContribution.type", target = "secType")
//    @Mapping(source = "individual.grossIncome", target = "grossIncome")
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
