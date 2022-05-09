package gr.senik.netcalculator.domain.model.tax

import gr.senik.common.domain.model.Money

class TaxTestHelper {
    companion object {
        fun incomeTaxLevels() = listOf(
            IncomeTaxLevel(TaxLevelType.FIRST_10K, Money(10_000), 0.09),
            IncomeTaxLevel(TaxLevelType.SECOND_10K, Money(10_000), 0.22),
            IncomeTaxLevel(TaxLevelType.THIRD_10K, Money(10_000), 0.28),
            IncomeTaxLevel(TaxLevelType.FOURTH_10K, Money(10_000), 0.36),
            IncomeTaxLevel(TaxLevelType.EXCESS, Money(Integer.MAX_VALUE), 0.44),
        )

        fun solidarityContributionTaxLevels() = listOf(
            SolidarityContributionTaxLevel(SolidarityContributionLevelType.FIRST_12K, Money(12_000), 0.0),
            SolidarityContributionTaxLevel(SolidarityContributionLevelType.SECOND_8K, Money(8_000), 0.022),
            SolidarityContributionTaxLevel(SolidarityContributionLevelType.THIRD_10K, Money(10_000), 0.05),
            SolidarityContributionTaxLevel(SolidarityContributionLevelType.FOURTH_10K, Money(10_000), 0.065),
            SolidarityContributionTaxLevel(SolidarityContributionLevelType.FIFTH_25K, Money(25_000), 0.075),
            SolidarityContributionTaxLevel(SolidarityContributionLevelType.SIXTH_155K, Money(155_000), 0.09),
            SolidarityContributionTaxLevel(SolidarityContributionLevelType.EXCESS, Money(Integer.MAX_VALUE), 0.1),
        )
    }
}
