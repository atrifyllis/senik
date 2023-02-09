package gr.senik.netcalculator.domain.model

import gr.senik.common.domain.model.DomainEvent
import gr.senik.common.domain.model.Money

class IncomeCalculated(
    val incomeId: IncomeId,
    val individualId: LegalEntityId,
    val income: Money,
) : DomainEvent(aggregateId = incomeId, aggregateType = "income")
