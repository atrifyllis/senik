package gr.senik.netcalculator.adapters.secondary.persistence

import gr.alx.common.adapters.secondary.persistence.BaseEntity
import gr.alx.common.domain.model.Money
import gr.senik.netcalculator.domain.model.InsuranceType
import gr.senik.netcalculator.domain.model.SECType
import jakarta.persistence.*
import java.util.*

@Entity
@Table(name = "individual")
class IndividualEntity(

    @Id
    override var id: UUID,

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    var insuranceType: InsuranceType,

    @AttributeOverride(name = "id", column = Column(name = "efka_class_id"))
    var efkaClassId: UUID,

    @AttributeOverride(name = "id", column = Column(name = "eteaep_class_id"))
    var eteaepClassId: UUID,

    @AttributeOverride(name = "amount", column = Column(name = "gross_annual_amount"))
    @AttributeOverride(name = "currencyCode", column = Column(name = "gross_annual_currency"))
    var grossIncome: Money,

    @AttributeOverride(name = "amount", column = Column(name = "annual_expenses_amount"))
    @AttributeOverride(name = "currencyCode", column = Column(name = "annual_expenses_currency"))
    var annualExpensesAmount: Money,

    var isLessThanFiveYears: Boolean = false,

    @Enumerated(EnumType.STRING)
    var secType: SECType = SECType.SINGLE_EMPLOYER_LARGE_AREA,

    var branches: Int = 1,
) : BaseEntity<UUID>()
