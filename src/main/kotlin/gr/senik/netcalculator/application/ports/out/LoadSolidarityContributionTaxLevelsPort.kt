package gr.senik.netcalculator.application.ports.out

import gr.senik.netcalculator.domain.model.tax.SolidarityContributionTaxLevel
import org.springframework.cache.annotation.Cacheable

sealed interface LoadSolidarityContributionTaxLevelsPort {
    @Cacheable("solidarityContributionTaxLevels")
    fun loadSolidarityContributionTaxLevels(): List<SolidarityContributionTaxLevel>
}
