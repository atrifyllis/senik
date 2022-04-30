package gr.senik.tax.domain.model

import gr.senik.common.domain.model.Money

class IncomeTaxLevel(
    private val type: TaxLevelType,
    levelLimit: Money,
    levelFactor: Double,
) : TaxLevel(levelLimit, levelFactor) {
}
