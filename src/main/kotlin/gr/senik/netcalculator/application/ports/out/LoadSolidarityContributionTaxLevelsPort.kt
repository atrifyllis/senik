package gr.senik.netcalculator.application.ports.out

import gr.senik.netcalculator.domain.model.v2.SolidarityTax
import gr.senik.netcalculator.domain.model.v2.TaxLevel
import org.springframework.cache.annotation.Cacheable

sealed interface LoadSolidarityContributionTaxLevelsPort {
    @Cacheable("solidarityContributionTaxLevels")
    fun loadSolidarityContributionTaxLevels(): List<TaxLevel>
    fun loadSolidarityContributionTax(): SolidarityTax
}
