package gr.senik.netcalculator.domain.model.tax

import gr.senik.netcalculator.domain.model.IncomeTax
import gr.senik.netcalculator.domain.model.SelfEmployedContribution
import gr.senik.netcalculator.domain.model.SolidarityTax

class TaxTestHelper {
    companion object {
        fun incomeTaxLevels() = listOf<IncomeTax>(
//            TaxLevel(TaxLevelType.FIRST_10K, Money(10_000), 0.09),
//            TaxLevel(TaxLevelType.SECOND_10K, Money(10_000), 0.22),
//            TaxLevel(TaxLevelType.THIRD_10K, Money(10_000), 0.28),
//            TaxLevel(TaxLevelType.FOURTH_10K, Money(10_000), 0.36),
//            TaxLevel(TaxLevelType.EXCESS, Money(Integer.MAX_VALUE), 0.44),
        )

        fun solidarityContributionTaxLevels() = listOf<SolidarityTax>(
//            TaxLevel(SolidarityContributionLevelType.FIRST_12K, Money(12_000), 0.0),
//            TaxLevel(SolidarityContributionLevelType.SECOND_8K, Money(8_000), 0.022),
//            TaxLevel(SolidarityContributionLevelType.THIRD_10K, Money(10_000), 0.05),
//            TaxLevel(SolidarityContributionLevelType.FOURTH_10K, Money(10_000), 0.065),
//            TaxLevel(SolidarityContributionLevelType.FIFTH_25K, Money(25_000), 0.075),
//            TaxLevel(SolidarityContributionLevelType.SIXTH_155K, Money(155_000), 0.09),
//            TaxLevel(SolidarityContributionLevelType.EXCESS, Money(Integer.MAX_VALUE), 0.1),
        )

        fun selfEmployedContributions() = listOf<SelfEmployedContribution>(
//            SelfEmployedContribution(SECType.SINGLE_EMPLOYER_LARGE_AREA, Money(500)),
        )
    }
}
