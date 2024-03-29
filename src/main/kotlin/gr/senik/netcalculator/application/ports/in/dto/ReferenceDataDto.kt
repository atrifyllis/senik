package gr.senik.netcalculator.application.ports.`in`.dto

import gr.alx.common.domain.model.Money
import gr.senik.netcalculator.domain.model.*

data class EteaepClassDto(
    val id: EteaepClassId,
    val type: EteaepClassType,
    val auxiliaryPensionAmount: Money,
    val lumpSumAmount: Money,
)

data class EfkaClassDto(
    val id: EfkaClassId,
    val type: EfkaClassType,
    val mainPensionAmount: Money,
    val healthCareMoneyAmount: Money,
    val healthCareKindAmount: Money,
    val unemploymentAmount: Money,
)

data class IncomeTaxLevelDto(
    val id: TaxLevelId,
    val type: IncomeTaxLevelType?,
    val levelLimit: Money,
    val levelFactor: Double,
)

data class SolidarityContributionTaxLevelDto(
    val id: TaxLevelId,
    val type: SolidarityContributionLevelType?,
    val levelLimit: Money,
    val levelFactor: Double,
)

data class SelfEmployedContributionDto(
    val id: SelfEmployedContributionId,
    val secType: SECType,
    val amount: Money,
)

data class InsuranceTypeDto(
    val id: String,
    val type: String,
)

data class ReferenceDataDto(
    val eteaepClasses: List<EteaepClassDto>,
    val efkaClasses: List<EfkaClassDto>,
    val incomeTaxLevels: List<IncomeTaxLevelDto>,
    val solidarityContributionTaxLevels: List<SolidarityContributionTaxLevelDto>,
    val selfEmployedContributions: List<SelfEmployedContributionDto>,
    val enabledInsuranceTypes: List<InsuranceTypeDto> = InsuranceType.values()
        .map { InsuranceTypeDto(it.name, it.name) }
        .toList(),
)
