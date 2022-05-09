package gr.senik.netcalculator.domain.model.tax

import gr.senik.common.domain.model.Money

class SolidarityContributionTax(
    taxableIncome: Money,
    taxLevels: List<SolidarityContributionTaxLevel>,
) : LevelBasedTax(taxableIncome, taxLevels)
