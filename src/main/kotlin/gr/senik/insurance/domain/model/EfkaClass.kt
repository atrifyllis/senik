package gr.senik.insurance.domain.model

import gr.senik.common.domain.model.AbstractAggregateRoot
import gr.senik.common.domain.model.DomainEntityId
import gr.senik.common.domain.model.Money
import java.util.*
import javax.persistence.EmbeddedId

class EfkaClassId(value: UUID?) : DomainEntityId(value)


class EfkaClass(

    private val type: EfkaClassType,

    private val mainPensionAmount: Money,

    private val healthCareMoneyAmount: Money,

    private val healthCareKindAmount: Money,

    ) : AbstractAggregateRoot<EfkaClass, EfkaClassId>() {

    @EmbeddedId
    private val id: EfkaClassId = EfkaClassId(UUID.randomUUID())

    override fun getId(): EfkaClassId = id

    override fun toString(): String {
        return "EfkaClass(type=$type, mainPensionAmount=$mainPensionAmount, healthCareMoneyAmount=$healthCareMoneyAmount, healthCareKindAmount=$healthCareKindAmount, id=$id) ${super.toString()}"
    }

    fun calculateTotalContributionAmount(): Money = mainPensionAmount + healthCareMoneyAmount + healthCareKindAmount

}
