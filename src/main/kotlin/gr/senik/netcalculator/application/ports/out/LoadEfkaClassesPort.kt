package gr.senik.netcalculator.application.ports.out

import gr.senik.netcalculator.domain.model.EfkaClass

sealed interface LoadEfkaClassesPort {
    fun loadEfkaClasses(): List<EfkaClass>
}
