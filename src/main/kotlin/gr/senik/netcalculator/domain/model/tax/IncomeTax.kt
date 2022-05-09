package gr.senik.netcalculator.domain.model.tax

import gr.senik.common.domain.model.Money

class IncomeTax(
    taxableIncome: Money,
    taxLevels: List<IncomeTaxLevel>,
) : LevelBasedTax(taxableIncome, taxLevels)
