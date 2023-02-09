package gr.senik.netcalculator.application.ports.out

import gr.senik.netcalculator.domain.model.IncomeTax
import gr.senik.netcalculator.domain.model.TaxLevel
import org.springframework.cache.annotation.Cacheable

sealed interface LoadIncomeTaxLevelsPort {
    @Cacheable("incomeTaxLevels")
    fun loadIncomeTaxLevels(): List<TaxLevel>

    fun loadIncomeTax(): IncomeTax
}
