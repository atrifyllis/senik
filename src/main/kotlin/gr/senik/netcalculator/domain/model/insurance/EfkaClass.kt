package gr.senik.netcalculator.domain.model.insurance

import gr.senik.common.domain.model.AbstractAggregateRoot
import gr.senik.common.domain.model.DomainEntityId
import gr.senik.common.domain.model.Money
import java.util.*
import javax.persistence.*

@Embeddable
class EfkaClassId(id: UUID?) : DomainEntityId(id)

@Entity
class EfkaClass(

    @EmbeddedId
    private val id: EfkaClassId = EfkaClassId(UUID.randomUUID()),

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


    ) : AbstractAggregateRoot<EfkaClass, EfkaClassId>() {

    override fun getId(): EfkaClassId = id

    val totalContributionAmount: Money
        get() = mainPensionAmount + healthCareMoneyAmount + healthCareKindAmount + unemploymentAmount

    override fun toString(): String {
        return "EfkaClass(id=$id, type=$type, mainPensionAmount=$mainPensionAmount, healthCareMoneyAmount=$healthCareMoneyAmount, healthCareKindAmount=$healthCareKindAmount, unemploymentAmount=$unemploymentAmount) ${super.toString()}"
    }

}
