package gr.senik.netcalculator.domain.model.tax

import gr.senik.common.domain.model.Money

class IncomeTaxLevel(
    private val type: TaxLevelType,
    levelLimit: Money,
    levelFactor: Double,
) : TaxLevel(levelLimit, levelFactor)
