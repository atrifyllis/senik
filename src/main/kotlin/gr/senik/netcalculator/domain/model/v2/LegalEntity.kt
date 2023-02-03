package gr.senik.netcalculator.domain.model.v2

import java.util.*

class LegalEntityId(val id: UUID) {
    companion object {
        fun generateId(): LegalEntityId {
            return LegalEntityId(UUID.randomUUID())
        }
    }
}

abstract class LegalEntity(
    val id: LegalEntityId,
    val type: LegalEntityType,
    val efkaClass: EfkaClass,
    val eteaepClass: EteaepClass,
    val selfEmployedContribution: SelfEmployedContribution,
    val incomeTax: IncomeTax,
    val solidarityTax: SolidarityTax,

    ) {
    abstract fun calculateIncome(): Income
}

enum class LegalEntityType {
    INDIVIDUAL, COMPANY
}







