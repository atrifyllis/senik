package gr.senik.netcalculator.adapters.secondary.persistence

import gr.senik.common.domain.model.Money
import gr.senik.netcalculator.domain.model.LegalEntityType
import gr.senik.netcalculator.domain.model.SolidarityContributionLevelType
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.Table
import java.util.*

@Entity
@Table(name = "solidarity_contribution_tax_level")
class SolidarityContributionLevelEntity(
    id: UUID,
    @Enumerated(EnumType.STRING)
    val type: SolidarityContributionLevelType,
    @Enumerated(EnumType.STRING)
    val legalEntityType: LegalEntityType,
    order: Int,
    levelLimit: Money,
    levelFactor: Double,
) : TaxLevelEntity(id, order, levelLimit, levelFactor)


