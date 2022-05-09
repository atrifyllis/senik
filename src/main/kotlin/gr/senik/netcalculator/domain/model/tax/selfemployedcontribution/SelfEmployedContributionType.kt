package gr.senik.netcalculator.domain.model.tax.selfemployedcontribution

import gr.senik.common.domain.model.Money

class SelfEmployedContributionType(
    val type: SECType,
    val taxAmount: Money,
)
