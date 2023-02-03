package gr.senik.netcalculator.adapters.secondary.persistence

import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface CalculatedIncomeRepository : JpaRepository<CalculatedIncomeEntity, UUID>
