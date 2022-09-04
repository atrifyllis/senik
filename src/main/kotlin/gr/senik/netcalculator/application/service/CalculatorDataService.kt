package gr.senik.netcalculator.application.service

import gr.senik.common.domain.model.Money
import gr.senik.netcalculator.application.ports.`in`.mapper.CalculationResultMapper
import gr.senik.netcalculator.application.ports.`in`.mapper.Dummy
import gr.senik.netcalculator.application.ports.`in`.mapper.IndividualMapper
import gr.senik.netcalculator.application.ports.`in`.mapper.ReferenceDataMapper
import gr.senik.netcalculator.application.ports.`in`.web.CalculateIncomeUseCase
import gr.senik.netcalculator.application.ports.`in`.web.LoadCalculatorDataUseCase
import gr.senik.netcalculator.application.ports.`in`.web.dto.CalculationCommand
import gr.senik.netcalculator.application.ports.`in`.web.dto.CalculationResultDto
import gr.senik.netcalculator.application.ports.`in`.web.dto.ReferenceDataDto
import gr.senik.netcalculator.application.ports.out.CalculateNetIncomePort
import gr.senik.netcalculator.application.ports.out.LoadReferenceDataPort
import gr.senik.netcalculator.domain.model.income.CalculatedNetIncome
import gr.senik.netcalculator.domain.model.insurance.InsuranceType
import org.ff4j.FF4j
import org.springframework.stereotype.Service

@Service
class CalculatorDataService(
    private val loadReferenceDataPort: LoadReferenceDataPort,
    private val calculateNetIncomePort: CalculateNetIncomePort,
    private val referenceDataMapper: ReferenceDataMapper,
    private val individualMapper: IndividualMapper,
    private val calculationResultMapper: CalculationResultMapper,
    private val fF4j: FF4j,
) : LoadCalculatorDataUseCase, CalculateIncomeUseCase {
    override fun getReferenceData(): ReferenceDataDto {
        val eteaepClasses = loadReferenceDataPort.loadEteaepClasses()
        val efkaClasses = loadReferenceDataPort.loadEfkaClasses()
        val incomeTaxLevels = loadReferenceDataPort.loadIncomeTaxLevels()
        val solidarityContributionTaxLevels = loadReferenceDataPort.loadSolidarityContributionTaxLevels()
        val selfEmployedContributions = loadReferenceDataPort.loadSelfEmployedContributions()
        val enabledInsuranceTypes = retrieveEnabledInsuranceTypes()
        return referenceDataMapper.toReferenceDataDto(
            Dummy(),
            efkaClasses,
            eteaepClasses,
            incomeTaxLevels,
            solidarityContributionTaxLevels,
            selfEmployedContributions,
            enabledInsuranceTypes
        )
    }

    override fun calculate(command: CalculationCommand): CalculationResultDto {
        val individual = individualMapper.toIndividual(command.individual)

        val efkaClasses = loadReferenceDataPort.loadEfkaClasses()
        val eteaepClasses = loadReferenceDataPort.loadEteaepClasses()
        val incomeTaxLevels = loadReferenceDataPort.loadIncomeTaxLevels()
        val solidarityContributionTaxLevels = loadReferenceDataPort.loadSolidarityContributionTaxLevels()
        val selfEmployedContributions = loadReferenceDataPort.loadSelfEmployedContributions()

        val efkaContributionAmount = efkaClasses.first { it.id == individual.efkaClassId }.totalContributionAmount
        val eteaepContributionAmount = eteaepClasses.first { it.id == individual.eteaepClassId }.totalContributionAmount

        val calculatedNetIncome = CalculatedNetIncome(individual = individual)

        val (insuranceCost, totalTax, netAnnualIncome) = calculatedNetIncome.calculateNetIncome(
            efkaContributionAmount = efkaContributionAmount,
            eteaepContributionAmount = eteaepContributionAmount,
            incomeTaxLevels = incomeTaxLevels,
            solidarityContributionTaxLevels = solidarityContributionTaxLevels,
            selfEmployedContributions = selfEmployedContributions
        )

        calculateNetIncomePort.persist(calculatedNetIncome)

        return calculationResultMapper.toCalculationResult(NetAnnualIncome(insuranceCost, totalTax, netAnnualIncome))
    }

    private fun retrieveEnabledInsuranceTypes(): List<InsuranceType> =
        InsuranceType.values().filter { fF4j.check(it.name) }

}

data class NetAnnualIncome(
    val insuranceCost: Money,
    val totalTax: Money,
    val netAnnualIncome: Money,
)
