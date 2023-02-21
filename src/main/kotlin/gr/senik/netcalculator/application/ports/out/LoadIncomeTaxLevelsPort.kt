package gr.senik.netcalculator.application.ports.out

import gr.senik.netcalculator.domain.model.IncomeTax
import gr.senik.netcalculator.domain.model.TaxLevel

sealed interface LoadIncomeTaxLevelsPort {
    fun loadIncomeTaxLevels(): List<TaxLevel>

    fun loadIncomeTax(): IncomeTax
}
