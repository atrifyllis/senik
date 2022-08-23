package gr.senik.netcalculator.adapters.secondary.persistence

import gr.senik.netcalculator.application.ports.out.CalculateNetIncomePort
import gr.senik.netcalculator.domain.model.income.CalculatedNetIncome
import org.springframework.stereotype.Service

@Service
class CalculatedNetIncomePersistenceAdapter(
    private val calculatedIncomeRepository: CalculatedIncomeRepository,
) : CalculateNetIncomePort {
    override fun persist(calculatedNetIncome: CalculatedNetIncome) {
        calculatedIncomeRepository.save(calculatedNetIncome)
    }
}
