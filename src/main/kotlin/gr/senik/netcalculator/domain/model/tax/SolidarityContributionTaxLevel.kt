package gr.senik.netcalculator.domain.model.tax

import gr.senik.common.domain.model.Money
import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated

@Entity
class SolidarityContributionTaxLevel(
    @Enumerated(EnumType.STRING)
    private val type: SolidarityContributionLevelType,
    levelLimit: Money,
    levelFactor: Double,
) : TaxLevel(levelLimit, levelFactor)
