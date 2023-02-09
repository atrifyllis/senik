package gr.senik.netcalculator.application.ports.out

import gr.senik.netcalculator.domain.model.EfkaClass
import org.springframework.cache.annotation.Cacheable

sealed interface LoadEfkaClassesPort {
    @Cacheable("efkaClasses")
    fun loadEfkaClasses(): List<EfkaClass>
}
