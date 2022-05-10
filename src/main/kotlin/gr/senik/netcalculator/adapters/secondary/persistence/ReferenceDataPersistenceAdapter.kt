package gr.senik.netcalculator.adapters.secondary.persistence

import gr.senik.netcalculator.application.ports.out.LoadReferenceDataPort
import gr.senik.netcalculator.domain.model.insurance.EfkaClass
import gr.senik.netcalculator.domain.model.insurance.EteaepClass
import gr.senik.netcalculator.domain.model.tax.IncomeTaxLevel
import gr.senik.netcalculator.domain.model.tax.SolidarityContributionTaxLevel
import org.springframework.stereotype.Service

@Service
class ReferenceDataPersistenceAdapter(
    private val efkaClassRepository: EfkaClassRepository,
    private val eteaepClassRepository: EteaepClassRepository,
    private val incomeTaxLevelRepository: IncomeTaxLevelRepository,
    private val solidarityContributionTaxLevelRepository: SolidarityContributionTaxLevelRepository
) : LoadReferenceDataPort {

    override fun loadEfkaClasses(): List<EfkaClass> = efkaClassRepository.findAll()

    override fun loadEteaepClasses(): List<EteaepClass> = eteaepClassRepository.findAll()

    override fun loadIncomeTaxLevels(): List<IncomeTaxLevel> = incomeTaxLevelRepository.findAll()

    override fun loadSolidarityContributionTaxLevels(): List<SolidarityContributionTaxLevel> = solidarityContributionTaxLevelRepository.findAll()
}
