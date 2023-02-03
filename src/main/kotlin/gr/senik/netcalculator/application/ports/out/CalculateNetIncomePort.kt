package gr.senik.netcalculator.application.ports.out

import gr.senik.netcalculator.domain.model.v2.Income
import gr.senik.netcalculator.domain.model.v2.Individual

interface CalculateNetIncomePort {

    fun persist(individual: Individual, income: Income)
}
