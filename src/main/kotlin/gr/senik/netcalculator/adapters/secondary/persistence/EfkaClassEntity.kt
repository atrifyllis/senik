package gr.senik.netcalculator.adapters.secondary.persistence

import gr.senik.common.domain.model.BaseEntity
import gr.senik.common.domain.model.Money
import gr.senik.netcalculator.domain.model.v2.EfkaClassType
import jakarta.persistence.*
import java.util.*

@Entity
@Table(name = "efka_class")
class EfkaClassEntity(

    @Id
    override val id: UUID,

    @Enumerated(EnumType.STRING)
    val type: EfkaClassType,

    @Embedded
    @AttributeOverride(name = "amount", column = Column(name = "main_pension_amount"))
    @AttributeOverride(name = "currencyCode", column = Column(name = "currency_main"))
    val mainPensionAmount: Money,

    @Embedded
    @AttributeOverride(name = "amount", column = Column(name = "health_care_money_amount"))
    @AttributeOverride(name = "currencyCode", column = Column(name = "currency_health_1"))
    val healthCareMoneyAmount: Money,

    @Embedded
    @AttributeOverride(name = "amount", column = Column(name = "health_care_kind_amount"))
    @AttributeOverride(name = "currencyCode", column = Column(name = "currency_health_2"))
    val healthCareKindAmount: Money,

    @Embedded
    @AttributeOverride(name = "amount", column = Column(name = "unemployment_amount"))
    @AttributeOverride(name = "currencyCode", column = Column(name = "currency_unemployment"))
    val unemploymentAmount: Money,
) : BaseEntity<UUID>()
