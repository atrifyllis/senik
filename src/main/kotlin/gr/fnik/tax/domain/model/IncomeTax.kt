package gr.fnik.tax.domain.model

import gr.fnik.common.domain.model.Money

class IncomeTax(
    taxableIncome: Money,
    taxLevels: List<IncomeTaxLevel>,
) : BaseTax(taxableIncome, taxLevels)
