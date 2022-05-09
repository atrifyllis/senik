package gr.senik.netcalculator.domain.model.insurance

import gr.senik.common.domain.model.AbstractAggregateRoot
import gr.senik.common.domain.model.DomainEntityId
import gr.senik.common.domain.model.Money
import java.util.*
import javax.persistence.*

@Embeddable
class EteaepClassId(var id: UUID?) : DomainEntityId(id)

@Entity

class EteaepClass(

    @EmbeddedId
    private val id: EteaepClassId = EteaepClassId(UUID.randomUUID()),

    @Enumerated(EnumType.STRING)
    private val type: EteaepClassType,

    @Embedded
    @AttributeOverride(name = "amount", column = Column(name = "auxiliary_pension_amount"))
    @AttributeOverride(name = "currencyCode", column = Column(name = "currency"))
    private val auxiliaryPensionAmount: Money,

    @Embedded
    @AttributeOverride(name = "amount", column = Column(name = "lump_sum_amount"))
    @AttributeOverride(name = "currencyCode", column = Column(name = "currency_transient", insertable = false, updatable = false))
    private val lumpSumAmount: Money,

    ) : AbstractAggregateRoot<EteaepClass, EteaepClassId>() {

    override fun getId(): EteaepClassId = id

    override fun toString(): String {
        return "EteaepClass(type=$type, auxiliaryPensionAmount=$auxiliaryPensionAmount, lumpSumAmount=$lumpSumAmount, id=$id) ${super.toString()}"
    }

    fun calculateTotalContributionAmount(): Money = auxiliaryPensionAmount + lumpSumAmount

}
