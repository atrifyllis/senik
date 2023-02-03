package gr.senik.netcalculator.domain.model.v2

import gr.senik.common.domain.model.Money
import java.util.*

class IndividualId(val id: UUID)

class Individual(
    id: LegalEntityId,
    type: LegalEntityType = LegalEntityType.INDIVIDUAL,
    val insuranceType: InsuranceType,
    efkaClass: EfkaClass,
    eteaepClass: EteaepClass,
    selfEmployedContribution: SelfEmployedContribution,
    incomeTax: IncomeTax,
    solidarityTax: SolidarityTax,
    grossIncome: Money?,
    private val grossDailyIncomes: List<DailyIncome>,
    val expensesAmount: Money,
    val branches: Int,
    val isLessThanFiveYears: Boolean,
) : LegalEntity(id, type, efkaClass, eteaepClass, selfEmployedContribution, incomeTax, solidarityTax) {
    val grossIncome: Money

    init {
        this.grossIncome = grossIncome ?: Money(grossDailyIncomes.map { it.dailyIncome * it.days }.sumOf { it.amount })
    }


    override fun calculateIncome(): Income {
        val insuranceCost = calculateInsuranceCost()
        val taxableIncome = grossIncome - insuranceCost - expensesAmount
        val incomeTaxAmount = incomeTax.calculateTax(taxableIncome)
        val solidarityContributionAmount = solidarityTax.calculateTax(taxableIncome)
        val selfEmployedContributionAmount = selfEmployedContribution.calculateTax(isLessThanFiveYears, branches)
        val totalTaxAmount = incomeTaxAmount + solidarityContributionAmount + selfEmployedContributionAmount

        val netIncome = grossIncome - totalTaxAmount - insuranceCost

        return Income(
            Income.generateId(),
            insuranceCost = insuranceCost,
            taxableIncome = taxableIncome,
            incomeTaxAmount = incomeTaxAmount,
            selfEmployedContributionAmount = selfEmployedContributionAmount,
            solidarityContributionAmount = solidarityContributionAmount,
            totalTaxAmount = totalTaxAmount,
            netIncome = netIncome
        )
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
    val selfEmployedContributionAmount: Money,
    val solidarityContributionAmount: Money,
    val totalTaxAmount: Money,
    val netIncome: Money,
) {
    companion object {
        fun generateId(): IncomeId {
            return IncomeId(UUID.randomUUID())
        }
    }
}

class IncomeId(val id: UUID)

data class DailyIncome(val days: Int, val dailyIncome: Money)

