package gr.senik.netcalculator.domain.model

import gr.alx.common.domain.model.Money
import gr.senik.netcalculator.domain.model.insurance.InsuranceTestHelper.Companion.efkaClasses
import gr.senik.netcalculator.domain.model.insurance.InsuranceTestHelper.Companion.eteaepClasses
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.util.UUID.fromString
import java.util.UUID.randomUUID

class IncomeTest {
    @Test
    fun `should calculate income`() {

        val incomeTax = IncomeTax(
//            id = IncomeTaxId(fromString("493d176f-2355-4526-8e0e-fb0391c486a2")),
            legalEntityType = LegalEntityType.INDIVIDUAL,
            taxLevels = TaxDataHelper.incomeTaxLevels()
        )

        val selfEmployedContribution = SelfEmployedContribution(
            id = SelfEmployedContributionId(fromString("4cbd2d39-7921-480a-9ab5-6a1ddf98f680")),
            secType = SECType.SINGLE_EMPLOYER_LARGE_AREA,
            Money(500)
        )

        val solidarityTax = SolidarityTax(
//            id = SolidarityTaxId(fromString("2495d138-455f-4074-9799-2d5a106f639a")),
            legalEntityType = LegalEntityType.INDIVIDUAL,
            taxLevels = TaxDataHelper.solidarityTaxLevels()
        )


        val individual = Individual(
            id = LegalEntityId(randomUUID()),
            insuranceType = InsuranceType.TSMEDE,
            efkaClass = efkaClasses.first(),
            eteaepClass = eteaepClasses.first(),
            selfEmployedContribution = selfEmployedContribution,
            incomeTax = incomeTax,
            solidarityTax = solidarityTax,
            grossAnnualIncome = Money(81_400),
            grossDailyIncomes = emptyList(),
            annualExpensesAmount = Money.ZERO,
            branches = 1,
            isLessThanFiveYears = false,
        )

        val cut: Income = individual.calculateIncome()

        assertThat(cut).isNotNull
        assertThat(cut.taxableIncome).isEqualTo(Money(77944.00))
        assertThat(cut.insuranceCost).isEqualTo(Money(3456.00))
        assertThat(cut.incomeTaxAmount).isEqualTo(Money(26195.36))
        assertThat(cut.selfEmployedContributionTax).isEqualTo(Money(500.00))
        assertThat(cut.solidarityContributionTax).isEqualTo(Money(4365.96))
        assertThat(cut.totalTax).isEqualTo(Money(31061.32))
        assertThat(cut.netIncome).isEqualTo(Money(46882.68))
    }


}


