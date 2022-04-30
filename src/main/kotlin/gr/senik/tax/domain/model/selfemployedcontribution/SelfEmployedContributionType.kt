package gr.senik.tax.domain.model.selfemployedcontribution

import gr.senik.common.domain.model.Money

class SelfEmployedContributionType(
    val type: SECType,
    val taxAmount: Money,
)
