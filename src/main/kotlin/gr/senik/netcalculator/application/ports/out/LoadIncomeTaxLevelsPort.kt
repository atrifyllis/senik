package gr.senik.netcalculator.application.ports.out

import gr.senik.netcalculator.domain.model.tax.IncomeTaxLevel

sealed interface LoadIncomeTaxLevelsPort {
    fun loadIncomeTaxLevels(): List<IncomeTaxLevel>
}
