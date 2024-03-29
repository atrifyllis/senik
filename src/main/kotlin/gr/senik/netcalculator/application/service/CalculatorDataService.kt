package gr.senik.netcalculator.application.service

import gr.senik.netcalculator.application.ports.`in`.CalculateIncomeUseCase
import gr.senik.netcalculator.application.ports.`in`.LoadCalculatorDataUseCase
import gr.senik.netcalculator.application.ports.`in`.dto.CalculationCommand
import gr.senik.netcalculator.application.ports.`in`.dto.CalculationResultDto
import gr.senik.netcalculator.application.ports.`in`.dto.ReferenceDataDto
import gr.senik.netcalculator.application.ports.`in`.mapper.CalculationResultMapper
import gr.senik.netcalculator.application.ports.`in`.mapper.IndividualMapper
import gr.senik.netcalculator.application.ports.`in`.mapper.ReferenceDataMapper
import gr.senik.netcalculator.application.ports.out.CalculateNetIncomePort
import gr.senik.netcalculator.application.ports.out.LoadReferenceDataPort
import gr.senik.netcalculator.domain.model.Income
import gr.senik.netcalculator.domain.model.InsuranceType
import gr.senik.netcalculator.domain.model.LegalEntityId
import org.ff4j.FF4j
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

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
            efkaClasses,
            eteaepClasses,
            incomeTaxLevels,
            solidarityContributionTaxLevels,
            selfEmployedContributions,
            enabledInsuranceTypes
        )
    }

    @Transactional
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
                .first { it.secType == command.individual.secType }

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

    private fun retrieveEnabledInsuranceTypes(): List<InsuranceType> =
        InsuranceType.values().filter { fF4j.check(it.name) }

}
