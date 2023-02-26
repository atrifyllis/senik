package gr.senik.netcalculator.adapters.secondary.persistence

import gr.alx.common.domain.model.BaseEntity
import gr.alx.common.domain.model.Money
import jakarta.persistence.*
import java.util.*

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
abstract class TaxLevelEntity(

    @Id
    override val id: UUID,

    val order: Int,

    @Embedded
    @AttributeOverride(name = "amount", column = Column(name = "level_limit_amount"))
    @AttributeOverride(name = "currencyCode", column = Column(name = "currency_level_limit"))
    val levelLimit: Money,

    val levelFactor: Double,
) : BaseEntity<UUID>()


