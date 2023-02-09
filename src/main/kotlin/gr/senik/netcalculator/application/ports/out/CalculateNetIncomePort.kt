package gr.senik.netcalculator.application.ports.out

import gr.senik.netcalculator.domain.model.Income
import gr.senik.netcalculator.domain.model.Individual

interface CalculateNetIncomePort {
    fun persist(individual: Individual, income: Income)
}
