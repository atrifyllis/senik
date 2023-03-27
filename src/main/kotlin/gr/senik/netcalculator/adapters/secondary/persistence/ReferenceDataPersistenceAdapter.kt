package gr.senik.netcalculator.adapters.secondary.persistence

import gr.senik.common.adapters.secondary.persistence.tables.references.EFKA_CLASS
import gr.senik.netcalculator.adapters.secondary.persistence.mapper.*
import gr.senik.netcalculator.application.ports.out.LoadReferenceDataPort
import gr.senik.netcalculator.domain.model.*
import org.jooq.DSLContext
import org.springframework.cache.annotation.Cacheable
import org.springframework.stereotype.Service

@Service
class ReferenceDataPersistenceAdapter(
    private val eteaepClassEntityMapper: EteaepClassEntityMapper,
    private val incomeTaxLevelEntityMapper: IncomeTaxLevelEntityMapper,
    private val solidarityContributionLevelEntityMapper: SolidarityContributionLevelEntityMapper,
    private val selfEmployedContributionEntityMapper: SelfEmployedContributionEntityMapper,
    private val eteaepClassRepository: EteaepClassRepository,
    private val incomeTaxLevelRepository: IncomeTaxLevelRepository,
    private val solidarityContributionTaxLevelRepository: SolidarityContributionTaxLevelRepository,
    private val selfEmployedContributionRepository: SelfEmployedContributionRepository,
    private val dslContext: DSLContext,
) : LoadReferenceDataPort {

    @Cacheable("efkaClasses")
    override fun loadEfkaClasses(): List<EfkaClass> {
        return dslContext.select().from(EFKA_CLASS).fetchInto(EfkaClass::class.java)
    }


    @Cacheable("eteaepClasses")
    override fun loadEteaepClasses(): List<EteaepClass> =
        eteaepClassEntityMapper.toEteaepClassModel(eteaepClassRepository.findAll())

    @Cacheable("incomeTaxLevels")
    override fun loadIncomeTaxLevels(): List<TaxLevel> =
        incomeTaxLevelEntityMapper.toIncomeTaxLevelModel(incomeTaxLevelRepository.findAll())

    override fun loadIncomeTax(): IncomeTax {
        val taxLevels = loadIncomeTaxLevels()
        return IncomeTax(
            legalEntityType = taxLevels.first().legalEntityType,
            taxLevels = taxLevels
        )
    }

    @Cacheable("solidarityContributionTaxLevels")
    override fun loadSolidarityContributionTaxLevels(): List<TaxLevel> =
        solidarityContributionLevelEntityMapper.toModel(solidarityContributionTaxLevelRepository.findAll())

    override fun loadSolidarityContributionTax(): SolidarityTax {
        val taxLevels = loadSolidarityContributionTaxLevels()
        return SolidarityTax(
            legalEntityType = taxLevels.first().legalEntityType,
            taxLevels = taxLevels
        )
    }

    @Cacheable("selfEmployedContributions")
    override fun loadSelfEmployedContributions(): List<SelfEmployedContribution> =
        selfEmployedContributionEntityMapper.toModel(selfEmployedContributionRepository.findAll())
}
