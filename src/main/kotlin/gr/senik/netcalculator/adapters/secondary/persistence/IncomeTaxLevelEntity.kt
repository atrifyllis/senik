package gr.senik.netcalculator.adapters.secondary.persistence

import gr.senik.common.domain.model.Money
import gr.senik.netcalculator.domain.model.v2.LegalEntityType
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
    val type: TaxLevelType,
    @Enumerated(EnumType.STRING)
    val legalEntityType: LegalEntityType,
    order: Int,
    levelLimit: Money,
    levelFactor: Double,
) : TaxLevelEntity(id, order, levelLimit, levelFactor)

enum class TaxLevelType {
    FIRST_10K, SECOND_10K, THIRD_10K, FOURTH_10K, EXCESS
}
