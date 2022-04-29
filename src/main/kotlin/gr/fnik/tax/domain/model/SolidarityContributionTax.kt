package gr.fnik.tax.domain.model

import gr.fnik.common.domain.model.Money

class SolidarityContributionTax(
    taxableIncome: Money,
    taxLevels: List<SolidarityContributionTaxLevel>,
) : BaseTax(taxableIncome, taxLevels)
