package gr.senik.netcalculator.application.ports.out

import gr.senik.netcalculator.domain.model.tax.IncomeTaxLevel
import org.springframework.cache.annotation.Cacheable

sealed interface LoadIncomeTaxLevelsPort {
    @Cacheable("incomeTaxLevels")
    fun loadIncomeTaxLevels(): List<IncomeTaxLevel>
}
