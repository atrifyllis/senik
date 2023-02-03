package gr.senik.netcalculator.adapters.secondary.persistence

import gr.senik.common.domain.model.BaseEntity
import gr.senik.common.domain.model.Money
import gr.senik.netcalculator.domain.model.v2.SECType
import jakarta.persistence.*
import java.util.*

@Entity
@Table(name = "self_employed_contribution")
class SelfEmployedContributionEntity(

    @Id
    override val id: UUID,

    @Enumerated(EnumType.STRING)
    val type: SECType,

    @Embedded
    @AttributeOverride(name = "amount", column = Column(name = "amount"))
    @AttributeOverride(name = "currencyCode", column = Column(name = "currency"))
    val amount: Money,
) : BaseEntity<UUID>()
