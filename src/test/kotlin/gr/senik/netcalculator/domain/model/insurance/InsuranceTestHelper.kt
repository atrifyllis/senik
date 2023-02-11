package gr.senik.netcalculator.domain.model.insurance

import gr.senik.common.domain.model.Money
import gr.senik.netcalculator.domain.model.*
import java.util.*

class InsuranceTestHelper {
    companion object {
        val EFKA_CLASS_ID_1 = EfkaClassId(UUID.fromString("d8f9f8f0-f8f8-11e9-b210-d663bd873d93"))
        private val ETEAEP_CLASS_ID_1 = EteaepClassId(UUID.fromString("d8f9f8f1-f8f8-11e9-b210-d663bd873d93"))

        val efkaClasses = listOf(
            EfkaClass(
                id = EFKA_CLASS_ID_1,
                type = EfkaClassType.FIRST,
                mainPensionAmount = Money(155),
                healthCareMoneyAmount = Money(5),
                healthCareKindAmount = Money(50),
                unemploymentAmount = Money(10),
            )
        )
        val eteaepClasses = listOf(
            EteaepClass(
                id = ETEAEP_CLASS_ID_1,
                type = EteaepClassType.FIRST,
                auxiliaryPensionAmount = Money(42),
                lumpSumAmount = Money(26),
            )
        )

        private val incomeTax = IncomeTax(
//            id = IncomeTaxId(UUID.randomUUID()),
            legalEntityType = LegalEntityType.INDIVIDUAL,
            taxLevels = TaxDataHelper.incomeTaxLevels()
        )
        private val solidarityTax = SolidarityTax(
//            id = SolidarityTaxId(UUID.randomUUID()),
            legalEntityType = LegalEntityType.INDIVIDUAL,
            taxLevels = TaxDataHelper.solidarityTaxLevels()
        )
        private val selfEmployedContribution = SelfEmployedContribution(
            id = SelfEmployedContributionId(UUID.randomUUID()),
            secType = SECType.SINGLE_EMPLOYER_LARGE_AREA,
            amount = Money(500)
        )
        val insurancePersonWithAnnualIncome = Individual(
            id = LegalEntityId(UUID.randomUUID()),
            insuranceType = InsuranceType.TSMEDE,
            efkaClass = efkaClasses.first(),
            eteaepClass = eteaepClasses.first(),
            selfEmployedContribution = selfEmployedContribution,
            incomeTax = incomeTax,
            solidarityTax = solidarityTax,
            grossAnnualIncome = Money(85_000),
            grossDailyIncomes = emptyList(),
            annualExpensesAmount = Money.ZERO,
            branches = 1,
            isLessThanFiveYears = true,
        )

        val individualWithDailyIncomes = Individual(
            id = LegalEntityId(UUID.randomUUID()),
            legalEntityType = LegalEntityType.INDIVIDUAL,
            insuranceType = InsuranceType.TSMEDE,
            efkaClass = efkaClasses.first(),
            eteaepClass = eteaepClasses.first(),
            selfEmployedContribution = selfEmployedContribution,
            incomeTax = incomeTax,
            solidarityTax = solidarityTax,
            grossAnnualIncome = null,
            grossDailyIncomes = listOf(
                DailyIncome(120, Money(240)),
                DailyIncome(100, Money(370))
            ),
            annualExpensesAmount = Money.ZERO,
            branches = 1,
            isLessThanFiveYears = true,
        )
    }
}
