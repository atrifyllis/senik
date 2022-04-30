package gr.senik.insurance.domain.model

import gr.senik.common.domain.model.AbstractAggregateRoot
import gr.senik.common.domain.model.DomainEntityId
import gr.senik.common.domain.model.Money
import java.util.*
import javax.persistence.EmbeddedId

class EteaepClassId(value: UUID?) : DomainEntityId(value)


class EteaepClass(

    private val type: EteaepClassType,

    private val auxiliaryPensionAmount: Money,

    private val lumpSumAmount: Money,


    ) : AbstractAggregateRoot<EteaepClass, EteaepClassId>() {

    @EmbeddedId
    private val id: EteaepClassId = EteaepClassId(UUID.randomUUID())

    override fun getId(): EteaepClassId = id

    override fun toString(): String {
        return "EteaepClass(type=$type, auxiliaryPensionAmount=$auxiliaryPensionAmount, lumpSumAmount=$lumpSumAmount, id=$id) ${super.toString()}"
    }

    fun calculateTotalContributionAmount(): Money = auxiliaryPensionAmount + lumpSumAmount

}
