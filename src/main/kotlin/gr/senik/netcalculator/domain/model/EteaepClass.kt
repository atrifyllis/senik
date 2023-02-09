package gr.senik.netcalculator.domain.model

import gr.senik.common.domain.model.Money
import java.util.*

class EteaepClassId(val id: UUID)

class EteaepClass(
    val id: EteaepClassId,
    val type: EteaepClassType,
    val auxiliaryPensionAmount: Money,
    val lumpSumAmount: Money,

    )

enum class EteaepClassType {
    FIRST, SECOND, THIRD
}
