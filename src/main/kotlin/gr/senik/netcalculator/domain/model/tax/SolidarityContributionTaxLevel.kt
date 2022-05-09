package gr.senik.netcalculator.domain.model.tax

import gr.senik.common.domain.model.Money

class SolidarityContributionTaxLevel(
    private val type: SolidarityContributionLevelType,
    levelLimit: Money,
    levelFactor: Double,
) : TaxLevel(levelLimit, levelFactor)
