package gr.senik.netcalculator.application.ports.out

import gr.senik.netcalculator.domain.model.SolidarityTax
import gr.senik.netcalculator.domain.model.TaxLevel
import org.springframework.cache.annotation.Cacheable

sealed interface LoadSolidarityContributionTaxLevelsPort {
    @Cacheable("solidarityContributionTaxLevels")
    fun loadSolidarityContributionTaxLevels(): List<TaxLevel>

    fun loadSolidarityContributionTax(): SolidarityTax
}
