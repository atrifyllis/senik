package gr.senik.netcalculator.application.ports.out

import gr.senik.netcalculator.domain.model.insurance.EteaepClass
import org.springframework.cache.annotation.Cacheable

sealed interface LoadEteaepClassesPort {
    @Cacheable("eteaepClasses")
    fun loadEteaepClasses(): List<EteaepClass>
}
