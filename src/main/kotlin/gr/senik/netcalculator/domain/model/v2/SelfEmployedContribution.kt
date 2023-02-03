package gr.senik.netcalculator.domain.model.v2

import gr.senik.common.domain.model.Money
import java.util.*

class SelfEmployedContributionId(val id: UUID)

class SelfEmployedContribution(
    val id: SelfEmployedContributionId,
    val type: SECType,
    val amount: Money,
) {
    fun calculateTax(isLessThanFiveYears: Boolean, branches: Int): Money {
        return if (isLessThanFiveYears) Money.ZERO else amount * branches
    }
}
