package gr.senik.netcalculator.domain.model.tax.selfemployedcontribution

import gr.senik.common.domain.model.Money

/**
 * Τέλος επιτιδεύματος <br/>
 * TODO missing the cases with more than one client:
 * https://www.aade.gr/menoy/hristikoi-odigoi/enarxi-epiheirimatikis-drastiriotitas/pliromi-etisioy-teloys-epitideymatos
 */
class SelfEmployedContributionTax(
    type: SelfEmployedContributionType,
    isLessThanFiveYears: Boolean = false,
) {
    // exempt when insurance person is registered for less than 5 years
    val totalTax: Money = if (isLessThanFiveYears) Money(0) else type.taxAmount

    fun totalTaxAmount(): Money = totalTax
}
