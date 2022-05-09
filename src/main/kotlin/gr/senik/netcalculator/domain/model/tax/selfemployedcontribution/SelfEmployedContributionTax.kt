package gr.senik.netcalculator.domain.model.tax.selfemployedcontribution

import gr.senik.common.domain.model.Money

/**
 * Τέλος επιτιδεύματος <br/>
 * TODO missing the cases with more than one client:
 * https://www.aade.gr/menoy/hristikoi-odigoi/enarxi-epiheirimatikis-drastiriotitas/pliromi-etisioy-teloys-epitideymatos
 */
class SelfEmployedContributionTax(
    type: SelfEmployedContributionType,
) {
    val totalTax: Money = type.taxAmount

    fun totalTaxAmount(): Money = totalTax
}
