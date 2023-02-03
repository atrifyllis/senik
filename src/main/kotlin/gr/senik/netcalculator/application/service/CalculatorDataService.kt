package gr.senik.netcalculator.application.service

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
import gr.senik.netcalculator.domain.model.v2.Income
import gr.senik.netcalculator.domain.model.v2.LegalEntityId
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

        val efkaClass = loadReferenceDataPort.loadEfkaClasses()
            .first { it.id.id == command.individual.efkaClassId }
        val eteaepClass =
            loadReferenceDataPort.loadEteaepClasses()
                .first { it.id.id == command.individual.eteaepClassId }

        val incomeTax = loadReferenceDataPort.loadIncomeTax()
        val solidarityContributionTax = loadReferenceDataPort.loadSolidarityContributionTax()

        val selfEmployedContribution =
            loadReferenceDataPort.loadSelfEmployedContributions()
                .first { it.type == command.individual.secType }

        val individual = individualMapper.toIndividual(
            LegalEntityId.generateId(),
            command.individual,
            efkaClass,
            eteaepClass,
            selfEmployedContribution,
            incomeTax,
            solidarityContributionTax
        )

        val income: Income = individual.calculateIncome()

        calculateNetIncomePort.persist(individual, income)

        return calculationResultMapper.toCalculationResult(income)
    }

    private fun retrieveEnabledInsuranceTypes(): List<gr.senik.netcalculator.domain.model.v2.InsuranceType> =
        gr.senik.netcalculator.domain.model.v2.InsuranceType.values().filter { fF4j.check(it.name) }

}
