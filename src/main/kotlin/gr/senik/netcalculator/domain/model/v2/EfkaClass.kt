package gr.senik.netcalculator.domain.model.v2

import gr.senik.common.domain.model.Money
import java.util.*

class EfkaClassId(val id: UUID)

class EfkaClass(
    val id: EfkaClassId,
    val type: EfkaClassType,
    val mainPensionAmount: Money,
    val healthCareMoneyAmount: Money,
    val healthCareKindAmount: Money,
    val unemploymentAmount: Money
)

enum class EfkaClassType {
    FIRST, SECOND, THIRD, FOURTH, FIFTH, SIXTH, NEW
}
