package gr.senik.netcalculator.domain.model.tax.selfemployedcontribution

import gr.senik.common.domain.model.Money

/**
 * Τέλος επιτιδεύματος <br/>
 * https://www.aade.gr/menoy/hristikoi-odigoi/enarxi-epiheirimatikis-drastiriotitas/pliromi-etisioy-teloys-epitideymatos
 */
class SelfEmployedContributionTax(
    private val type: SECType,
    private val branches: Int = 1,
    isLessThanFiveYears: Boolean = false,
    private val selfEmployedContributions: List<SelfEmployedContribution>,
) {
    // exempt when insurance person is registered for less than 5 years
    val totalTax: Money = if (isLessThanFiveYears) Money.ZERO
    else calculateTax()

    private fun calculateTax(): Money {
        val taxAmount = selfEmployedContributions.find { it.type == type }?.amount ?: Money.ZERO
        return taxAmount * branches
    }

    fun totalTaxAmount(): Money = totalTax
}
