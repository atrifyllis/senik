package gr.senik.netcalculator.application.service

import gr.senik.netcalculator.application.ports.`in`.mapper.Dummy
import gr.senik.netcalculator.application.ports.`in`.mapper.ReferenceDataMapper
import gr.senik.netcalculator.application.ports.`in`.web.ReferenceDataUseCase
import gr.senik.netcalculator.application.ports.`in`.web.dto.ReferenceDataDto
import gr.senik.netcalculator.application.ports.out.LoadReferenceDataPort
import org.springframework.stereotype.Service

@Service
class ReferenceDataService(
    private val loadReferenceDataPort: LoadReferenceDataPort,
    private val referenceDataMapper: ReferenceDataMapper
) : ReferenceDataUseCase {
    override fun getReferenceData(): ReferenceDataDto {
        val eteaepClasses = loadReferenceDataPort.loadEteaepClasses()
        val efkaClasses = loadReferenceDataPort.loadEfkaClasses()
        val incomeTaxLevels = loadReferenceDataPort.loadIncomeTaxLevels()
        val solidarityContributionTaxLevels = loadReferenceDataPort.loadSolidarityContributionTaxLevels()
        return referenceDataMapper.toReferenceDataDto(Dummy(), efkaClasses, eteaepClasses, incomeTaxLevels, solidarityContributionTaxLevels)
    }
}
