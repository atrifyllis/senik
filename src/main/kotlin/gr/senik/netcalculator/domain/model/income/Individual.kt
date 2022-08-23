package gr.senik.netcalculator.domain.model.income

import gr.senik.common.domain.model.BaseEntity
import gr.senik.common.domain.model.DomainEntityId
import gr.senik.common.domain.model.Money
import gr.senik.netcalculator.domain.model.insurance.EfkaClassId
import gr.senik.netcalculator.domain.model.insurance.EteaepClassId
import gr.senik.netcalculator.domain.model.insurance.InsuranceType
import gr.senik.netcalculator.domain.model.tax.selfemployedcontribution.SECType
import java.util.*
import javax.persistence.*

@Embeddable
class IndividualId(id: UUID?) : DomainEntityId(id)

@Entity
class Individual(


    @Enumerated(EnumType.STRING)
    var type: InsuranceType,

    @AttributeOverride(name = "id", column = Column(name = "efka_class_id"))
    var efkaClassId: EfkaClassId,

    @AttributeOverride(name = "id", column = Column(name = "eteaep_class_id"))
    var eteaepClassId: EteaepClassId,

    @Transient
    val grossAnnualIncome: Money?,
    @Transient
    val grossDailyIncomes: List<DailyIncome>,

    @AttributeOverride(name = "amount", column = Column(name = "annual_expenses_amount"))
    @AttributeOverride(name = "currencyCode", column = Column(name = "annual_expenses_currency"))
    var annualExpensesAmount: Money,

    var isLessThanFiveYears: Boolean = false,

    @Enumerated(EnumType.STRING)
    var secType: SECType = SECType.SINGLE_EMPLOYER_LARGE_AREA,

    var branches: Int = 1,

    ) : BaseEntity<IndividualId>() {

    @EmbeddedId
    private val id: IndividualId = IndividualId(UUID.randomUUID())

    @AttributeOverride(name = "amount", column = Column(name = "gross_annual_amount"))
    @AttributeOverride(name = "currencyCode", column = Column(name = "gross_annual_currency"))
    var grossIncome: Money = grossAnnualIncome ?: sumGrossDailyAmounts()

    private fun sumGrossDailyAmounts() = Money(
        grossDailyIncomes
            .map { it.dailyIncome * it.days }
            .sumOf { it.amount }
    )

    override fun getId(): IndividualId = id
}

data class DailyIncome(val days: Int, val dailyIncome: Money)
