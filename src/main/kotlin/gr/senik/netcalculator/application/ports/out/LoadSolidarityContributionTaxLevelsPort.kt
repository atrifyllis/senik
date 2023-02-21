package gr.senik.netcalculator.application.ports.out

import gr.senik.netcalculator.domain.model.SolidarityTax
import gr.senik.netcalculator.domain.model.TaxLevel

sealed interface LoadSolidarityContributionTaxLevelsPort {
    fun loadSolidarityContributionTaxLevels(): List<TaxLevel>

    fun loadSolidarityContributionTax(): SolidarityTax
}
