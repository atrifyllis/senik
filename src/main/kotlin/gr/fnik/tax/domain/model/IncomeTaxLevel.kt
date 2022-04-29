package gr.fnik.tax.domain.model

import gr.fnik.common.domain.model.Money

class IncomeTaxLevel(
    private val type: TaxLevelType,
    levelLimit: Money,
    levelFactor: Double,
) : TaxLevel(levelLimit, levelFactor) {
}
