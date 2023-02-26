package gr.senik.netcalculator.domain.model

import gr.alx.common.domain.model.Money
import java.util.*

class SelfEmployedContributionId(val id: UUID)

class SelfEmployedContribution(
    val id: SelfEmployedContributionId,
    val secType: SECType,
    val amount: Money,
) {
    fun calculateTax(isLessThanFiveYears: Boolean, branches: Int): Money {
        return if (isLessThanFiveYears) Money.ZERO else amount * branches
    }
}

enum class SECType {
    SINGLE_EMPLOYER_SMALL_AREA,
    SINGLE_EMPLOYER_LARGE_AREA,
    SINGLE_EMPLOYER_WITH_BRANCHES,

    MULTIPLE_EMPLOYERS,
    MULTIPLE_EMPLOYERS_WITH_BRANCHES,
}
