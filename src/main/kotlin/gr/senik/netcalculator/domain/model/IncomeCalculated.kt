package gr.senik.netcalculator.domain.model

import gr.alx.common.domain.model.DomainEvent
import gr.alx.common.domain.model.Money

data class IncomeCalculated(
    val incomeId: IncomeId,
    val individualId: LegalEntityId,
    val income: Money,
) : DomainEvent(aggregateId = incomeId, aggregateType = "income")
