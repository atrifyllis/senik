package gr.senik.netcalculator.application.ports.out

import gr.senik.netcalculator.domain.model.EteaepClass

sealed interface LoadEteaepClassesPort {
    fun loadEteaepClasses(): List<EteaepClass>
}
