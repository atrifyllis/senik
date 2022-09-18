package gr.senik.netcalculator.domain.model.income

import gr.senik.common.domain.model.DomainEvent
import gr.senik.common.domain.model.Money

data class NetIncomeCalculated(val calculatedNetIncomeId: CalculatedNetIncomeId, val netAnnualIncome: Money) :
    DomainEvent(aggregateId = calculatedNetIncomeId, aggregateType = "calculatedNetIncome")
