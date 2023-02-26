package gr.senik.netcalculator.domain.model

import gr.alx.common.domain.model.DomainEntityId
import gr.alx.common.domain.model.Money
import java.util.*

class Individual(
    id: LegalEntityId,
    legalEntityType: LegalEntityType? = LegalEntityType.INDIVIDUAL,
    val insuranceType: InsuranceType,
    efkaClass: EfkaClass,
    eteaepClass: EteaepClass,
    selfEmployedContribution: SelfEmployedContribution,
    incomeTax: IncomeTax,
    solidarityTax: SolidarityTax,
    grossAnnualIncome: Money?,
    grossDailyIncomes: List<DailyIncome>,
    val annualExpensesAmount: Money,
    val branches: Int,
    val isLessThanFiveYears: Boolean,
) : LegalEntity(id, legalEntityType, efkaClass, eteaepClass, selfEmployedContribution, incomeTax, solidarityTax) {
    val grossIncome: Money

    init {
        this.grossIncome =
            grossAnnualIncome ?: Money(grossDailyIncomes.map { it.dailyIncome * it.days }.sumOf { it.amount })
    }


    override fun calculateIncome(): Income {
        val insuranceCost = calculateInsuranceCost()
        val taxableIncome = grossIncome - insuranceCost - annualExpensesAmount
        val incomeTaxAmount = incomeTax.calculateTax(taxableIncome)
        val solidarityContributionAmount = solidarityTax.calculateTax(taxableIncome)
        val selfEmployedContributionAmount = selfEmployedContribution.calculateTax(isLessThanFiveYears, branches)
        val totalTaxAmount = incomeTaxAmount + solidarityContributionAmount + selfEmployedContributionAmount

        val netIncome = grossIncome - totalTaxAmount - insuranceCost

        val income = Income(
            Income.generateId(),
            insuranceCost = insuranceCost,
            taxableIncome = taxableIncome,
            incomeTaxAmount = incomeTaxAmount,
            selfEmployedContributionTax = selfEmployedContributionAmount,
            solidarityContributionTax = solidarityContributionAmount,
            totalTax = totalTaxAmount,
            netIncome = netIncome
        )

        registerEvent(
            IncomeCalculated(
                incomeId = income.id,
                individualId = this.id,
                income = income.netIncome,
            )
        )

        return income
    }


    private fun calculateInsuranceCost(): Money {
        val eteaepCost = when (insuranceType) {
            // TODO check if any of the insurance types have no eteaep contributions
            InsuranceType.TSMEDE, InsuranceType.OAEE,
            InsuranceType.OGA, InsuranceType.TSAY,
            -> eteaepClass.auxiliaryPensionAmount + eteaepClass.lumpSumAmount
        }
        return (efkaClass.mainPensionAmount +
                efkaClass.healthCareKindAmount +
                efkaClass.healthCareMoneyAmount +
                efkaClass.unemploymentAmount +
                eteaepCost) * 12
    }
}

enum class InsuranceType {
    TSMEDE, // engineers
    OAEE, // merchants etc.
    TSAY, // doctors etc.
    OGA // farmers etc.
}

class Income(
    val id: IncomeId,
    val insuranceCost: Money,
    val taxableIncome: Money,
    val incomeTaxAmount: Money,
    val selfEmployedContributionTax: Money,
    val solidarityContributionTax: Money,
    val totalTax: Money,
    val netIncome: Money,
) {
    companion object {
        fun generateId(): IncomeId {
            return IncomeId(UUID.randomUUID())
        }
    }
}

class IncomeId(id: UUID) : DomainEntityId(id)

data class DailyIncome(val days: Int, val dailyIncome: Money)

