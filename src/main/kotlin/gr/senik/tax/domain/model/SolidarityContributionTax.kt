package gr.senik.tax.domain.model

import gr.senik.common.domain.model.Money

class SolidarityContributionTax(
    taxableIncome: Money,
    taxLevels: List<SolidarityContributionTaxLevel>,
) : BaseTax(taxableIncome, taxLevels)
