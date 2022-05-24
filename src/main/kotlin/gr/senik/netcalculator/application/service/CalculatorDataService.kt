package gr.senik.netcalculator.application.service

import gr.senik.common.domain.model.Money
import gr.senik.netcalculator.application.ports.`in`.mapper.Dummy
import gr.senik.netcalculator.application.ports.`in`.mapper.IndividualMapper
import gr.senik.netcalculator.application.ports.`in`.mapper.ReferenceDataMapper
import gr.senik.netcalculator.application.ports.`in`.web.CalculateIncomeUseCase
import gr.senik.netcalculator.application.ports.`in`.web.LoadCalculatorDataUseCase
import gr.senik.netcalculator.application.ports.`in`.web.dto.CalculationCommand
import gr.senik.netcalculator.application.ports.`in`.web.dto.CalculationResultDto
import gr.senik.netcalculator.application.ports.`in`.web.dto.ReferenceDataDto
import gr.senik.netcalculator.application.ports.out.LoadReferenceDataPort
import gr.senik.netcalculator.domain.model.tax.IncomeTax
import gr.senik.netcalculator.domain.model.tax.SolidarityContributionTax
import gr.senik.netcalculator.domain.model.tax.selfemployedcontribution.SECType
import gr.senik.netcalculator.domain.model.tax.selfemployedcontribution.SelfEmployedContributionTax
import gr.senik.netcalculator.domain.model.tax.selfemployedcontribution.SelfEmployedContributionType
import gr.senik.netcalculator.domain.service.InsuranceCostCalculator
import gr.senik.netcalculator.domain.service.NetIncomeCalculator
import gr.senik.netcalculator.domain.service.TaxableIncomeCalculator
import gr.senik.netcalculator.domain.service.TotalTaxCalculator
import org.springframework.stereotype.Service

@Service
class CalculatorDataService(
    private val loadReferenceDataPort: LoadReferenceDataPort,
    private val referenceDataMapper: ReferenceDataMapper,
    private val individualMapper: IndividualMapper,
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

        val insuranceCostCalculator = InsuranceCostCalculator(
            individual = individual,
            efkaClasses = efkaClasses,
            eteaepClasses = eteaepClasses,
        )
        val insuranceCost = insuranceCostCalculator.calculateYearlyInsuranceCost()

        val taxableIncomeCalculator = TaxableIncomeCalculator(individual, insuranceCost)
        val taxableIncome = taxableIncomeCalculator.calculateTaxableIncome()

        val incomeTax = IncomeTax(
            taxableIncome = taxableIncome,
            taxLevels = incomeTaxLevels,

            )
        val solidarityContributionTax = SolidarityContributionTax(
            taxableIncome = taxableIncome,
            taxLevels = solidarityContributionTaxLevels,
        )
        // TODO: this should be in the database
        val selfEmployedContributionTax = SelfEmployedContributionTax(
            type = SelfEmployedContributionType(SECType.SINGLE_EMPLOYER_LARGE_AREA, Money(500))
        )
        val totalTaxCalculator = TotalTaxCalculator(
            incomeTax = incomeTax,
            solidarityContributionTax = solidarityContributionTax,
            selfEmployedContributionTax = selfEmployedContributionTax,
        )
        val totalTax = totalTaxCalculator.calculateTotalTax()

        val netIncomeCalculator = NetIncomeCalculator(
            individual = individual,
            insuranceCost = insuranceCost,
            totalTax = totalTax
        )
        val netIncome = netIncomeCalculator.calculateNetIncome()

        return CalculationResultDto(netIncome)
    }
}
