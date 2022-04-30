package gr.senik.tax.domain.model

import gr.senik.common.domain.model.Money

class SolidarityContributionTaxLevel(
    private val type: SolidarityContributionLevelType,
    levelLimit: Money,
    levelFactor: Double,
) : TaxLevel(levelLimit, levelFactor)
