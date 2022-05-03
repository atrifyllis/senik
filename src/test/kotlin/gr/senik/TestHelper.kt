package gr.senik

import gr.senik.common.domain.model.Money
import gr.senik.insurance.domain.model.*
import java.util.*

class TestHelper {
    companion object {
        val EFKA_CLASS_ID_1 = EfkaClassId(UUID.fromString("d8f9f8f0-f8f8-11e9-b210-d663bd873d93"))
        val ETEAEP_CLASS_ID_1 = EteaepClassId(UUID.fromString("d8f9f8f1-f8f8-11e9-b210-d663bd873d93"))

        val efkaClasses = listOf<EfkaClass>(
            EfkaClass(
                id = EFKA_CLASS_ID_1,
                type = EfkaClassType.FIRST,
                mainPensionAmount = Money(155),
                healthCareMoneyAmount = Money(5),
                healthCareKindAmount = Money(50),
                unemploymentAmount = Money(10),
            )
        )
        val eteaepClasses = listOf<EteaepClass>(
            EteaepClass(
                id = ETEAEP_CLASS_ID_1,
                type = EteaepClassType.FIRST,
                auxiliaryPensionAmount = Money(42),
                lumpSumAmount = Money(26),
            )
        )
    }
}
