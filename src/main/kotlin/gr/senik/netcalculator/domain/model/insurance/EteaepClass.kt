package gr.senik.netcalculator.domain.model.insurance

import gr.senik.common.domain.model.AbstractAggregateRoot
import gr.senik.common.domain.model.DomainEntityId
import gr.senik.common.domain.model.Money
import java.util.*
import javax.persistence.*

@Embeddable
class EteaepClassId(id: UUID?) : DomainEntityId(id)

@Entity

class EteaepClass(

    @EmbeddedId
    override val id: EteaepClassId = EteaepClassId(UUID.randomUUID()),

    @Enumerated(EnumType.STRING)
    val type: EteaepClassType,

    @Embedded
    @AttributeOverride(name = "amount", column = Column(name = "auxiliary_pension_amount"))
    @AttributeOverride(name = "currencyCode", column = Column(name = "currency_aux"))
    val auxiliaryPensionAmount: Money,

    @Embedded
    @AttributeOverride(name = "amount", column = Column(name = "lump_sum_amount"))
    @AttributeOverride(name = "currencyCode", column = Column(name = "currency_lump"))
    val lumpSumAmount: Money,

    ) : AbstractAggregateRoot<EteaepClass, EteaepClassId>() {

    val totalContributionAmount: Money
        get() = auxiliaryPensionAmount + lumpSumAmount

    override fun toString(): String {
        return "EteaepClass(type=$type, auxiliaryPensionAmount=$auxiliaryPensionAmount, lumpSumAmount=$lumpSumAmount, id=$id) ${super.toString()}"
    }
}
