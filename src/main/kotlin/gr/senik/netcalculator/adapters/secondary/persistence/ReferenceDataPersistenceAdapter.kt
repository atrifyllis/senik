package gr.senik.netcalculator.adapters.secondary.persistence

import gr.senik.netcalculator.adapters.secondary.persistence.mapper.*
import gr.senik.netcalculator.application.ports.out.LoadReferenceDataPort
import gr.senik.netcalculator.domain.model.v2.*
import org.springframework.stereotype.Service

@Service
class ReferenceDataPersistenceAdapter(
    private val efkaClassRepository: EfkaClassRepository,
    private val efkaClassEntityMapper: EfkaClassEntityMapper,
    private val eteaepClassEntityMapper: EteaepClassEntityMapper,
    private val incomeTaxLevelEntityMapper: IncomeTaxLevelEntityMapper,
    private val solidarityContributionLevelEntityMapper: SolidarityContributionLevelEntityMapper,
    private val selfEmployedContributionEntityMapper: SelfEmployedContributionEntityMapper,
    private val eteaepClassRepository: EteaepClassRepository,
    private val incomeTaxLevelRepository: IncomeTaxLevelRepository,
    private val solidarityContributionTaxLevelRepository: SolidarityContributionTaxLevelRepository,
    private val selfEmployedContributionRepository: SelfEmployedContributionRepository,
) : LoadReferenceDataPort {

    override fun loadEfkaClasses(): List<EfkaClass> =
        efkaClassEntityMapper.toEfkaClassModel(efkaClassRepository.findAll())

    override fun loadEteaepClasses(): List<EteaepClass> =
        eteaepClassEntityMapper.toEteaepClassModel(eteaepClassRepository.findAll())

    override fun loadIncomeTaxLevels(): List<TaxLevel> =
        incomeTaxLevelEntityMapper.toIncomeTaxLevelModel(incomeTaxLevelRepository.findAll())

    override fun loadIncomeTax(): IncomeTax {
        val taxLevels = loadIncomeTaxLevels()
        return IncomeTax(
            legalEntityType = taxLevels.first().legalEntityType,
            taxLevels = taxLevels
        )
    }

    override fun loadSolidarityContributionTaxLevels(): List<TaxLevel> =
        solidarityContributionLevelEntityMapper.toModel(solidarityContributionTaxLevelRepository.findAll())

    override fun loadSolidarityContributionTax(): SolidarityTax {
        val taxLevels = loadSolidarityContributionTaxLevels()
        return SolidarityTax(
            legalEntityType = taxLevels.first().legalEntityType,
            taxLevels = taxLevels
        )
    }

    override fun loadSelfEmployedContributions(): List<SelfEmployedContribution> =
        selfEmployedContributionEntityMapper.toModel(selfEmployedContributionRepository.findAll())
}
