package gr.senik.netcalculator.domain.model.v2

import gr.senik.common.domain.model.AbstractAggregateRoot
import gr.senik.common.domain.model.DomainEntityId
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
    val type: LegalEntityType,
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







