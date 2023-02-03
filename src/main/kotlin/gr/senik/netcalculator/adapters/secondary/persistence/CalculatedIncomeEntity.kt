package gr.senik.netcalculator.adapters.secondary.persistence

import gr.senik.common.domain.model.BaseEntity
import gr.senik.common.domain.model.Money
import jakarta.persistence.*
import java.util.*

@Entity
@Table(name = "calculated_net_income")
class CalculatedIncomeEntity(

    @Id
    override var id: UUID,

    @OneToOne(cascade = [CascadeType.ALL])
    @JoinColumn(name = "individual_id", referencedColumnName = "id")
    var individual: IndividualEntity,

    @Embedded
    @AttributeOverride(name = "amount", column = Column(name = "insurance_amount"))
    @AttributeOverride(name = "currencyCode", column = Column(name = "insurance_currency"))
    var insuranceCost: Money = Money.ZERO,

    @Embedded
    @AttributeOverride(name = "amount", column = Column(name = "income_tax_amount"))
    @AttributeOverride(name = "currencyCode", column = Column(name = "income_tax_currency"))
    var incomeTax: Money = Money.ZERO,

    @Embedded
    @AttributeOverride(name = "amount", column = Column(name = "solidarity_contribution_tax_amount"))
    @AttributeOverride(name = "currencyCode", column = Column(name = "solidarity_contribution_tax_currency"))
    var solidarityContributionTax: Money = Money.ZERO,

    @Embedded
    @AttributeOverride(name = "amount", column = Column(name = "self_employed_contribution_tax_amount"))
    @AttributeOverride(name = "currencyCode", column = Column(name = "self_employed_contribution_tax_currency"))
    var selfEmployedContributionTax: Money = Money.ZERO,

    @Embedded
    @AttributeOverride(name = "amount", column = Column(name = "total_tax_amount"))
    @AttributeOverride(name = "currencyCode", column = Column(name = "total_tax_currency"))
    var totalTax: Money = Money.ZERO,

    @Embedded
    @AttributeOverride(name = "amount", column = Column(name = "net_annual_income_amount"))
    @AttributeOverride(name = "currencyCode", column = Column(name = "net_annual_income_currency"))
    var netAnnualIncome: Money = Money.ZERO,
) : BaseEntity<UUID>()
