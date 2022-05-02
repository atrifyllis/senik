package gr.senik.insurance.domain.model

import gr.senik.common.domain.model.AbstractAggregateRoot
import gr.senik.common.domain.model.DomainEntityId
import gr.senik.common.domain.model.Money
import java.util.*
import javax.persistence.EmbeddedId

class EfkaClassId(value: UUID?) : DomainEntityId(value)


class EfkaClass(

    @EmbeddedId
    private val id: EfkaClassId = EfkaClassId(UUID.randomUUID()),

    val type: EfkaClassType,

    private val mainPensionAmount: Money,

    private val healthCareMoneyAmount: Money,

    private val healthCareKindAmount: Money,

    private val unemploymentAmount: Money

) : AbstractAggregateRoot<EfkaClass, EfkaClassId>() {

    override fun getId(): EfkaClassId = id

    fun calculateTotalContributionAmount(): Money = mainPensionAmount + healthCareMoneyAmount + healthCareKindAmount + unemploymentAmount
    override fun toString(): String {
        return "EfkaClass(id=$id, type=$type, mainPensionAmount=$mainPensionAmount, healthCareMoneyAmount=$healthCareMoneyAmount, healthCareKindAmount=$healthCareKindAmount, unemploymentAmount=$unemploymentAmount) ${super.toString()}"
    }

}
