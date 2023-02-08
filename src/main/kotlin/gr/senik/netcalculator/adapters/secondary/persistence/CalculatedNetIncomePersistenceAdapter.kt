package gr.senik.netcalculator.adapters.secondary.persistence

import gr.senik.netcalculator.adapters.secondary.persistence.mapper.CalculatedIncomeEntityMapper
import gr.senik.netcalculator.application.ports.out.CalculateNetIncomePort
import gr.senik.netcalculator.domain.model.v2.Income
import gr.senik.netcalculator.domain.model.v2.Individual
import org.springframework.stereotype.Service

@Service
class CalculatedNetIncomePersistenceAdapter(
    private val calculatedIncomeRepository: CalculatedIncomeRepository,
    private val calculatedIncomeEntityMapper: CalculatedIncomeEntityMapper,
    private val domainEventPublisher: DomainEventPublisher,

    ) : CalculateNetIncomePort {
    override fun persist(individual: Individual, income: Income) {
        calculatedIncomeRepository.save(
            calculatedIncomeEntityMapper.toEntity(
                individual = individual,
                income = income
            )
        )
        domainEventPublisher.publishEvents(individual)
    }
}
