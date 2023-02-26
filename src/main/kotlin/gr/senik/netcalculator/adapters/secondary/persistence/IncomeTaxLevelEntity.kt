package gr.senik.netcalculator.adapters.secondary.persistence

import gr.alx.common.domain.model.Money
import gr.senik.netcalculator.domain.model.IncomeTaxLevelType
import gr.senik.netcalculator.domain.model.LegalEntityType
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.Table
import java.util.*

@Entity
@Table(name = "income_tax_level")
class IncomeTaxLevelEntity(
    id: UUID,
    @Enumerated(EnumType.STRING)
    val type: IncomeTaxLevelType,
    @Enumerated(EnumType.STRING)
    val legalEntityType: LegalEntityType,
    order: Int,
    levelLimit: Money,
    levelFactor: Double,
) : TaxLevelEntity(id, order, levelLimit, levelFactor)
