package gr.senik.netcalculator.application.ports.`in`.web.dto

import gr.senik.common.domain.model.Money
import gr.senik.netcalculator.domain.model.insurance.EfkaClassId
import gr.senik.netcalculator.domain.model.insurance.EfkaClassType
import gr.senik.netcalculator.domain.model.insurance.EteaepClassId
import gr.senik.netcalculator.domain.model.insurance.EteaepClassType
import gr.senik.netcalculator.domain.model.tax.SolidarityContributionLevelType
import gr.senik.netcalculator.domain.model.tax.TaxLevelId
import gr.senik.netcalculator.domain.model.tax.TaxLevelType
import gr.senik.netcalculator.domain.model.tax.selfemployedcontribution.SelfEmployedContribution

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

data class ReferenceDataDto(
    val eteaepClasses: List<EteaepClassDto>,
    val efkaClasses: List<EfkaClassDto>,
    val incomeTaxLevels: List<IncomeTaxLevelDto>,
    val solidarityContributionTaxLevels: List<SolidarityContributionTaxLevelDto>,
    val selfEmployedContributions: List<SelfEmployedContribution>,
)
