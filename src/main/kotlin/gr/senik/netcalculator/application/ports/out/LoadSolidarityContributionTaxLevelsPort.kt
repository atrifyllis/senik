package gr.senik.netcalculator.application.ports.out

import gr.senik.netcalculator.domain.model.tax.SolidarityContributionTaxLevel

sealed interface LoadSolidarityContributionTaxLevelsPort {
    fun loadSolidarityContributionTaxLevels(): List<SolidarityContributionTaxLevel>
}
