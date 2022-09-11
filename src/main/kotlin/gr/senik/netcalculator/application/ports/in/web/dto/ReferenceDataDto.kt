package gr.senik.netcalculator.application.ports.`in`.web.dto

import gr.senik.common.domain.model.Money
import gr.senik.netcalculator.domain.model.insurance.*
import gr.senik.netcalculator.domain.model.tax.SolidarityContributionLevelType
import gr.senik.netcalculator.domain.model.tax.TaxLevelId
import gr.senik.netcalculator.domain.model.tax.TaxLevelType
import gr.senik.netcalculator.domain.model.tax.selfemployedcontribution.SECType
import gr.senik.netcalculator.domain.model.tax.selfemployedcontribution.SelfEmployedContributionId

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
    val type: TaxLevelType,
    val levelLimit: Money,
    val levelFactor: Double,
)

data class SolidarityContributionTaxLevelDto(
    val id: TaxLevelId,
    val type: SolidarityContributionLevelType,
    val levelLimit: Money,
    val levelFactor: Double,
)

data class SelfEmployedContributionDto(
    val id: SelfEmployedContributionId,
    val type: SECType,
    val amount: Money,
)

data class ReferenceDataDto(
    val eteaepClasses: List<EteaepClassDto>,
    val efkaClasses: List<EfkaClassDto>,
    val incomeTaxLevels: List<IncomeTaxLevelDto>,
    val solidarityContributionTaxLevels: List<SolidarityContributionTaxLevelDto>,
    val selfEmployedContributions: List<SelfEmployedContributionDto>,
    val enabledInsuranceTypes: List<InsuranceType> = InsuranceType.values().toList(),
)
