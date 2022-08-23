package gr.senik.netcalculator.adapters.secondary.persistence

import gr.senik.netcalculator.domain.model.income.CalculatedNetIncome
import gr.senik.netcalculator.domain.model.income.CalculatedNetIncomeId
import org.springframework.data.jpa.repository.JpaRepository

interface CalculatedIncomeRepository : JpaRepository<CalculatedNetIncome, CalculatedNetIncomeId>
