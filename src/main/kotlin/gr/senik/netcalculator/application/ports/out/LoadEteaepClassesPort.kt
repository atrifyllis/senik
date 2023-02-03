package gr.senik.netcalculator.application.ports.out

import gr.senik.netcalculator.domain.model.v2.EteaepClass
import org.springframework.cache.annotation.Cacheable

sealed interface LoadEteaepClassesPort {
    @Cacheable("eteaepClasses")
    fun loadEteaepClasses(): List<EteaepClass>
}
