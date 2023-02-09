package gr.senik.netcalculator.domain.model

import gr.senik.common.domain.model.Money
import java.util.*

class TaxDataHelper {
    companion object {
        fun incomeTaxLevels(): List<TaxLevel> {
            return listOf(
                TaxLevel(
                    TaxLevelId(UUID.randomUUID()),
                    LegalEntityType.INDIVIDUAL,
                    1,
                    Money(10_000),
                    0.09
                ),
                TaxLevel(TaxLevelId(UUID.randomUUID()), LegalEntityType.INDIVIDUAL, 2, Money(10_000), 0.22),
                TaxLevel(TaxLevelId(UUID.randomUUID()), LegalEntityType.INDIVIDUAL, 3, Money(10_000), 0.28),
                TaxLevel(TaxLevelId(UUID.randomUUID()), LegalEntityType.INDIVIDUAL, 4, Money(10_000), 0.36),
                TaxLevel(TaxLevelId(UUID.randomUUID()), LegalEntityType.INDIVIDUAL, 5, Money(Integer.MAX_VALUE), 0.44),
            )
        }

        fun solidarityTaxLevels(): List<TaxLevel> {
            return listOf(
                TaxLevel(TaxLevelId(UUID.randomUUID()), LegalEntityType.INDIVIDUAL, 1, Money(12_000), 0.0),
                TaxLevel(TaxLevelId(UUID.randomUUID()), LegalEntityType.INDIVIDUAL, 2, Money(8_000), 0.022),
                TaxLevel(TaxLevelId(UUID.randomUUID()), LegalEntityType.INDIVIDUAL, 3, Money(10_000), 0.05),
                TaxLevel(TaxLevelId(UUID.randomUUID()), LegalEntityType.INDIVIDUAL, 4, Money(10_000), 0.065),
                TaxLevel(TaxLevelId(UUID.randomUUID()), LegalEntityType.INDIVIDUAL, 5, Money(25_000), 0.075),
                TaxLevel(TaxLevelId(UUID.randomUUID()), LegalEntityType.INDIVIDUAL, 6, Money(155_000), 0.09),
                TaxLevel(TaxLevelId(UUID.randomUUID()), LegalEntityType.INDIVIDUAL, 7, Money(Integer.MAX_VALUE), 0.1),
            )
        }

        fun selfEmployedContributions() = listOf(
            SelfEmployedContribution(
                SelfEmployedContributionId(UUID.randomUUID()),
                SECType.SINGLE_EMPLOYER_LARGE_AREA,
                Money(500)
            ),
        )
    }
}
