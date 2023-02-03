package gr.senik.netcalculator.application.ports.out

import gr.senik.netcalculator.domain.model.v2.EfkaClass
import org.springframework.cache.annotation.Cacheable

sealed interface LoadEfkaClassesPort {
    @Cacheable("efkaClasses")
    fun loadEfkaClasses(): List<EfkaClass>
}
