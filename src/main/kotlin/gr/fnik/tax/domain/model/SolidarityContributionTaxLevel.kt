package gr.fnik.tax.domain.model

import gr.fnik.common.domain.model.Money

class SolidarityContributionTaxLevel(
    private val type: SolidarityContributionLevelType,
    levelLimit: Money,
    levelFactor: Double,
) : TaxLevel(levelLimit, levelFactor)
