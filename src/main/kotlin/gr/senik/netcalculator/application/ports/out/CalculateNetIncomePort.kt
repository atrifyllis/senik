package gr.senik.netcalculator.application.ports.out

import gr.senik.netcalculator.domain.model.income.CalculatedNetIncome

interface CalculateNetIncomePort {

    fun persist(calculatedNetIncome: CalculatedNetIncome)
}
