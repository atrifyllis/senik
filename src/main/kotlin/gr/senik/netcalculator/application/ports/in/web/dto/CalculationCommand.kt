package gr.senik.netcalculator.application.ports.`in`.web.dto

import gr.senik.common.domain.model.Money
import gr.senik.netcalculator.domain.model.DailyIncome
import gr.senik.netcalculator.domain.model.InsuranceType
import gr.senik.netcalculator.domain.model.LegalEntityType
import gr.senik.netcalculator.domain.model.SECType
import java.util.*

data class CalculationCommand(val individual: IndividualDto)

data class IndividualDto(
    val legalEntityType: LegalEntityType = LegalEntityType.INDIVIDUAL,
    val insuranceType: InsuranceType,
    val efkaClassId: UUID,
    val eteaepClassId: UUID,
    val grossAnnualIncome: Money?,
    val grossDailyIncomes: List<DailyIncome>,
    val annualExpensesAmount: Money,
    val isLessThanFiveYears: Boolean = false,
    val secType: SECType = SECType.SINGLE_EMPLOYER_LARGE_AREA,
    val branches: Int = 1,
)


data class CalculationResultDto(
    val netIncome: Money,
    var taxableIncome: Money,
    val insuranceCost: Money,
    val totalTax: Money,
    val solidarityContributionTax: Money,
    val selfEmployedContributionTax: Money,
) {
    val monthlyIncome: Money = netIncome / 12
}
