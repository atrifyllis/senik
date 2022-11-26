package gr.senik.netcalculator.domain.model.tax

import gr.senik.common.domain.model.Money
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated

@Entity
class IncomeTaxLevel(
    @Enumerated(EnumType.STRING)
    val type: TaxLevelType,
    levelLimit: Money,
    levelFactor: Double,
) : TaxLevel(levelLimit, levelFactor)
