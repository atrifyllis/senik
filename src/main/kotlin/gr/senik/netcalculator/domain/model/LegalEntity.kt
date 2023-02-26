package gr.senik.netcalculator.domain.model

import gr.alx.common.domain.model.AbstractAggregateRoot
import gr.alx.common.domain.model.DomainEntityId
import java.util.*

class LegalEntityId(id: UUID) : DomainEntityId(id) {
    companion object {
        fun generateId(): LegalEntityId {
            return LegalEntityId(UUID.randomUUID())
        }
    }
}

abstract class LegalEntity(
    override val id: LegalEntityId,
    val type: LegalEntityType? = LegalEntityType.INDIVIDUAL,
    val efkaClass: EfkaClass,
    val eteaepClass: EteaepClass,
    val selfEmployedContribution: SelfEmployedContribution,
    val incomeTax: IncomeTax,
    val solidarityTax: SolidarityTax,

    ) : AbstractAggregateRoot<LegalEntity, LegalEntityId>() {
    abstract fun calculateIncome(): Income
}

enum class LegalEntityType {
    INDIVIDUAL, COMPANY
}







