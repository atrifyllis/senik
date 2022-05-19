package gr.senik.netcalculator.application.service

import gr.senik.netcalculator.application.ports.`in`.mapper.Dummy
import gr.senik.netcalculator.application.ports.`in`.mapper.ReferenceDataMapper
import gr.senik.netcalculator.application.ports.`in`.web.LoadCalculatorDataUseCase
import gr.senik.netcalculator.application.ports.`in`.web.dto.ReferenceDataDto
import gr.senik.netcalculator.application.ports.out.LoadReferenceDataPort
import org.springframework.stereotype.Service

@Service
class CalculatorDataService(
    private val loadReferenceDataPort: LoadReferenceDataPort,
    private val referenceDataMapper: ReferenceDataMapper
) : LoadCalculatorDataUseCase {
    override fun getReferenceData(): ReferenceDataDto {
        val eteaepClasses = loadReferenceDataPort.loadEteaepClasses()
        val efkaClasses = loadReferenceDataPort.loadEfkaClasses()
        val incomeTaxLevels = loadReferenceDataPort.loadIncomeTaxLevels()
        val solidarityContributionTaxLevels = loadReferenceDataPort.loadSolidarityContributionTaxLevels()
        return referenceDataMapper.toReferenceDataDto(Dummy(), efkaClasses, eteaepClasses, incomeTaxLevels, solidarityContributionTaxLevels)
    }
}
