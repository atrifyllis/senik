package gr.senik.netcalculator.domain.model.income

import gr.senik.common.domain.model.AbstractAggregateRoot
import gr.senik.common.domain.model.DomainEntityId
import gr.senik.common.domain.model.Money
import gr.senik.netcalculator.domain.model.insurance.InsuranceType
import gr.senik.netcalculator.domain.model.tax.IncomeTax
import gr.senik.netcalculator.domain.model.tax.IncomeTaxLevel
import gr.senik.netcalculator.domain.model.tax.SolidarityContributionTax
import gr.senik.netcalculator.domain.model.tax.SolidarityContributionTaxLevel
import gr.senik.netcalculator.domain.model.tax.selfemployedcontribution.SelfEmployedContribution
import gr.senik.netcalculator.domain.model.tax.selfemployedcontribution.SelfEmployedContributionTax
import mu.KotlinLogging
import java.util.*
import javax.persistence.*

private val log = KotlinLogging.logger {}

@Embeddable
class CalculatedNetIncomeId(id: UUID?) : DomainEntityId(id)

@Entity
@Table(name = "calculated_net_income")
class CalculatedNetIncome(
    @EmbeddedId
    private val id: CalculatedNetIncomeId = CalculatedNetIncomeId(UUID.randomUUID()),

    @OneToOne(cascade = [CascadeType.ALL])
    @JoinColumn(name = "individual_id", referencedColumnName = "id")
    val individual: Individual,

    ) : AbstractAggregateRoot<CalculatedNetIncome, CalculatedNetIncomeId>() {

    @Embedded
    @AttributeOverride(name = "amount", column = Column(name = "insurance_amount"))
    @AttributeOverride(name = "currencyCode", column = Column(name = "insurance_currency"))
    private var insuranceCost: Money = Money.ZERO

    @Transient
    private var taxableIncome: Money = Money.ZERO

    @Embedded
    @AttributeOverride(name = "amount", column = Column(name = "income_tax_amount"))
    @AttributeOverride(name = "currencyCode", column = Column(name = "income_tax_currency"))
    private lateinit var incomeTax: Money

    @Embedded
    @AttributeOverride(name = "amount", column = Column(name = "solidarity_contribution_tax_amount"))
    @AttributeOverride(name = "currencyCode", column = Column(name = "solidarity_contribution_tax_currency"))
    private lateinit var solidarityContributionTax: Money

    @Embedded
    @AttributeOverride(name = "amount", column = Column(name = "self_employed_contribution_tax_amount"))
    @AttributeOverride(name = "currencyCode", column = Column(name = "self_employed_contribution_tax_currency"))
    private lateinit var selfEmployedContributionTax: Money

    @Embedded
    @AttributeOverride(name = "amount", column = Column(name = "total_tax_amount"))
    @AttributeOverride(name = "currencyCode", column = Column(name = "total_tax_currency"))
    private lateinit var totalTax: Money


    @Embedded
    @AttributeOverride(name = "amount", column = Column(name = "net_annual_income_amount"))
    @AttributeOverride(name = "currencyCode", column = Column(name = "net_annual_income_currency"))
    private lateinit var netAnnualIncome: Money

    override fun getId(): CalculatedNetIncomeId = id

    fun calculateNetIncome(
        efkaContributionAmount: Money,
        eteaepContributionAmount: Money,
        incomeTaxLevels: List<IncomeTaxLevel>,
        solidarityContributionTaxLevels: List<SolidarityContributionTaxLevel>,
        selfEmployedContributions: List<SelfEmployedContribution>,
    ): Triple<Money, Money, Money> {
        calculateInsuranceCost(efkaContributionAmount, eteaepContributionAmount)
        calculateTaxableIncome()
        calculateIncomeTax(incomeTaxLevels)
        calculateSolidarityContributionTax(solidarityContributionTaxLevels)
        calculateSelfEmployedContributionTax(selfEmployedContributions)
        calculateTotalTax()
        log.info { "Insurance cost: $insuranceCost" }
        log.info { "Total tax: $totalTax" }
        this.netAnnualIncome = individual.grossIncome - totalTax - insuranceCost
        return Triple(insuranceCost, totalTax, netAnnualIncome)
    }

    private fun calculateInsuranceCost(efkaContributionAmount: Money, eteaepContributionAmount: Money) {
        val eteaepCost: Money = when (individual.type) {
            // TODO check if any of the insurance types have no eteaep contributions
            InsuranceType.TSMEDE, InsuranceType.OAEE,
            InsuranceType.OGA, InsuranceType.TSAY,
            -> eteaepContributionAmount
        }
        this.insuranceCost = (efkaContributionAmount + eteaepCost) * 12
    }

    private fun calculateTaxableIncome() {
        this.taxableIncome = individual.grossIncome - this.insuranceCost - individual.annualExpensesAmount
    }

    private fun calculateIncomeTax(taxLevels: List<IncomeTaxLevel>) {
        this.incomeTax = IncomeTax(taxableIncome, taxLevels).totalTaxAmount
    }

    private fun calculateSolidarityContributionTax(taxLevels: List<SolidarityContributionTaxLevel>) {
        this.solidarityContributionTax = SolidarityContributionTax(taxableIncome, taxLevels).totalTaxAmount
    }

    private fun calculateSelfEmployedContributionTax(
        selfEmployedContributions: List<SelfEmployedContribution>,
    ) {
        this.selfEmployedContributionTax =
            SelfEmployedContributionTax(
                type = individual.secType,
                branches = individual.branches,
                isLessThanFiveYears = individual.isLessThanFiveYears,
                selfEmployedContributions = selfEmployedContributions
            )
                .totalTaxAmount
    }

    private fun calculateTotalTax() {
        log.info { "income Tax: $incomeTax" }
        log.info { "solidarity contribution tax: $solidarityContributionTax" }
        log.info { "self employed contribution Tax: $selfEmployedContributionTax" }
        this.totalTax = incomeTax +
                solidarityContributionTax +
                selfEmployedContributionTax
    }


}
