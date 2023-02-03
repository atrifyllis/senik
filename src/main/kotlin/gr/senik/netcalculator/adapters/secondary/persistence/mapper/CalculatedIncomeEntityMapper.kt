package gr.senik.netcalculator.adapters.secondary.persistence.mapper

import gr.senik.netcalculator.adapters.secondary.persistence.CalculatedIncomeEntity
import gr.senik.netcalculator.domain.model.v2.*
import org.mapstruct.InjectionStrategy
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.ReportingPolicy
import java.util.*

@Mapper(
    componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE,
    uses = [
        IndividualEntityMapper::class
    ], injectionStrategy = InjectionStrategy.CONSTRUCTOR
)
abstract class CalculatedIncomeEntityMapper {

    @Mapping(source = "income", target = ".")
    @Mapping(source = "income.solidarityContributionAmount", target = "solidarityContributionTax")
    @Mapping(source = "income.selfEmployedContributionAmount", target = "selfEmployedContributionTax")
    @Mapping(source = "income.totalTaxAmount", target = "totalTax")
    @Mapping(source = "income.netIncome", target = "netAnnualIncome")
    @Mapping(source = "income.id", target = "id")
    @Mapping(source = "income.incomeTaxAmount", target = "incomeTax")
    abstract fun toEntity(individual: Individual, income: Income): CalculatedIncomeEntity


    fun map(id: LegalEntityId): UUID {
        return id.id
    }

    fun map(id: EfkaClass): UUID {
        return id.id.id
    }

    fun map(id: EteaepClass): UUID {
        return id.id.id
    }

    fun map(id: IncomeId): UUID {
        return id.id
    }
}
