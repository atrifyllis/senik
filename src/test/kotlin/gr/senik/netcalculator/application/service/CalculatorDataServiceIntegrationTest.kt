package gr.senik.netcalculator.application.service

import gr.senik.common.domain.model.Money
import gr.senik.netcalculator.IntegrationTestBase
import gr.senik.netcalculator.application.ports.`in`.web.dto.CalculationCommand
import gr.senik.netcalculator.application.ports.`in`.web.dto.IndividualDto
import gr.senik.netcalculator.domain.model.DailyIncome
import gr.senik.netcalculator.domain.model.EfkaClassType
import gr.senik.netcalculator.domain.model.EteaepClassType
import gr.senik.netcalculator.domain.model.InsuranceType
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import java.util.*

internal class CalculatorDataServiceIntegrationTest : IntegrationTestBase() {

    @Autowired
    lateinit var calculatorDataService: CalculatorDataService

    @Test
    fun `should return reference data`() {

        val (eteaepClasses, efkaClasses, incomeTaxLevels, solidarityContributionTaxLevels) =
            calculatorDataService.getReferenceData()

        assertThat(eteaepClasses[0].type).isEqualTo(EteaepClassType.FIRST)
        assertThat(eteaepClasses[0].lumpSumAmount).isEqualTo(Money(26))

        assertThat(efkaClasses[1].type).isEqualTo(EfkaClassType.SECOND)
        assertThat(efkaClasses[1].mainPensionAmount).isEqualTo(Money(186))

//        assertThat(incomeTaxLevels[2].type).isEqualTo(TaxLevelType.THIRD_10K)
        assertThat(incomeTaxLevels[2].levelFactor).isEqualTo(0.28)

//        assertThat(solidarityContributionTaxLevels[4].type).isEqualTo(SolidarityContributionLevelType.FIFTH_25K)
        assertThat(solidarityContributionTaxLevels[4].levelLimit).isEqualTo(Money(25_000))
    }

    @Test
    fun `should calculate net income`() {
        val command = CalculationCommand(
            individual = IndividualDto(
                insuranceType = InsuranceType.TSMEDE,
                efkaClassId = UUID.fromString("d55ec320-c0fe-4222-808e-3b52d9087061"),
                eteaepClassId = UUID.fromString("14d0b02a-2898-4c7b-8519-3bf163f8f931"),
                grossDailyIncomes = listOf(DailyIncome(days = 220, dailyIncome = Money(370))),
                annualExpensesAmount = Money.ZERO,
                grossAnnualIncome = null
            )
        )
        val result = calculatorDataService.calculate(command)

        assertThat(result.netIncome).isEqualTo(Money(47018.68))
    }
}
