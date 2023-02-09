package gr.senik.netcalculator.domain.model

import gr.senik.common.domain.model.Money
import java.util.*

abstract class LevelBasedTax(
    val legalEntityType: LegalEntityType,
    private val taxLevels: List<TaxLevel>,
) {
    fun calculateTax(taxableIncome: Money): Money {
        var remainingIncome = taxableIncome
        var totalAmount = Money.ZERO
        for (level in taxLevels.sortedBy { it.order }) {
            totalAmount += level.calculateLevelAmount(remainingIncome)
            remainingIncome -= level.levelLimit
        }
        return totalAmount
    }
}

class SolidarityTaxId(val id: UUID)

class SolidarityTax(
//    id: SolidarityTaxId,
    legalEntityType: LegalEntityType,
    taxLevels: List<TaxLevel>,
) : LevelBasedTax(legalEntityType, taxLevels)

enum class SolidarityContributionLevelType {
    FIRST_12K, SECOND_8K, THIRD_10K, FOURTH_10K, FIFTH_25K, SIXTH_155K, EXCESS
}

class IncomeTaxId(val id: UUID)

class IncomeTax(
//    val id: IncomeTaxId,
    legalEntityType: LegalEntityType,
    taxLevels: List<TaxLevel>,
) : LevelBasedTax(legalEntityType, taxLevels)

enum class IncomeTaxLevelType {
    FIRST_10K, SECOND_10K, THIRD_10K, FOURTH_10K, EXCESS
}


class TaxLevelId(val id: UUID)

class TaxLevel(
    val id: TaxLevelId,
    val legalEntityType: LegalEntityType,
    val order: Int,
    val levelLimit: Money,
    val levelFactor: Double,
) {
    // TODO this is ugly
    fun calculateLevelAmount(remainingAmount: Money): Money {
        if (remainingAmount < Money.ZERO) return Money.ZERO
        val taxableAmount: Money = calculateTaxableAmount(remainingAmount)
        return taxableAmount * levelFactor
    }

    private fun calculateTaxableAmount(remainingAmount: Money) =
        if (remainingAmount <= levelLimit) remainingAmount else levelLimit
}
