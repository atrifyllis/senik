package gr.senik.netcalculator.adapters.secondary.persistence

import gr.senik.common.domain.model.BaseEntity
import gr.senik.common.domain.model.Money
import gr.senik.netcalculator.domain.model.EteaepClassType
import jakarta.persistence.*
import java.util.*

@Entity
@Table(name = "eteaep_class")
class EteaepClassEntity(

    @Id
    override val id: UUID,

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
) : BaseEntity<UUID>()
