package gr.senik.tax.domain.model

import gr.senik.tax.domain.model.selfemployedcontribution.SelfEmployedContributionTax

class TotalTax(
    val incomeTax: IncomeTax,
    val solidarityContributionTax: SolidarityContributionTax,
    val selfEmployedContributionTax: SelfEmployedContributionTax,
)
