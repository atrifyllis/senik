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
import gr.senik.netcalculator.application.ports.out.LoadReferenceDataPort
import gr.senik.netcalculator.domain.service.NetIncomeCalculator
import mu.KotlinLogging
import org.springframework.stereotype.Service

private val log = KotlinLogging.logger {}

// TODO: this should be in the database
private const val SELF_EMPLOYED_CONTRIBUTION_TAX = 500

@Service
class CalculatorDataService(
    private val loadReferenceDataPort: LoadReferenceDataPort,
    private val referenceDataMapper: ReferenceDataMapper,
    private val individualMapper: IndividualMapper,
    private val calculationResultMapper: CalculationResultMapper,
) : LoadCalculatorDataUseCase, CalculateIncomeUseCase {
    override fun getReferenceData(): ReferenceDataDto {
        val eteaepClasses = loadReferenceDataPort.loadEteaepClasses()
        val efkaClasses = loadReferenceDataPort.loadEfkaClasses()
        val incomeTaxLevels = loadReferenceDataPort.loadIncomeTaxLevels()
        val solidarityContributionTaxLevels = loadReferenceDataPort.loadSolidarityContributionTaxLevels()
        return referenceDataMapper.toReferenceDataDto(Dummy(), efkaClasses, eteaepClasses, incomeTaxLevels, solidarityContributionTaxLevels)
    }

    override fun calculate(command: CalculationCommand): CalculationResultDto {
        val individual = individualMapper.toIndividual(command.individual)

        val efkaClasses = loadReferenceDataPort.loadEfkaClasses()
        val eteaepClasses = loadReferenceDataPort.loadEteaepClasses()
        val incomeTaxLevels = loadReferenceDataPort.loadIncomeTaxLevels()
        val solidarityContributionTaxLevels = loadReferenceDataPort.loadSolidarityContributionTaxLevels()

        val netIncomeCalculator = NetIncomeCalculator(
            individual = individual,
            efkaClasses = efkaClasses,
            eteaepClasses = eteaepClasses,
            incomeTaxLevels = incomeTaxLevels,
            solidarityContributionTaxLevels = solidarityContributionTaxLevels,
            selfEmployedContributionTaxAmount = SELF_EMPLOYED_CONTRIBUTION_TAX
        )
        val result = netIncomeCalculator.calculateNetIncome()

        return calculationResultMapper.toCalculationResult(result)
    }
}
