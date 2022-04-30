package gr.senik.tax.domain.model

import gr.senik.common.domain.model.Money

class IncomeTax(
    taxableIncome: Money,
    taxLevels: List<IncomeTaxLevel>,
) : LevelBasedTax(taxableIncome, taxLevels)
