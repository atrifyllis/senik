package gr.senik.insurance.domain.service

import gr.senik.common.domain.model.Money
import gr.senik.insurance.domain.model.*
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.util.*

internal class InsuranceCostCalculatorTest {
    companion object {
        private val EFKA_CLASS_ID_1 = EfkaClassId(UUID.fromString("d8f9f8f0-f8f8-11e9-b210-d663bd873d93"))
        private val ETEAEP_CLASS_ID_1 = EteaepClassId(UUID.fromString("d8f9f8f1-f8f8-11e9-b210-d663bd873d93"))
    }

    @Test
    fun `should calculate total insurance cost`() {
        val insuredPerson = InsuredPerson(
            type = InsuranceType.TSMEDE,
            efkaClassId = EFKA_CLASS_ID_1,
            eteaepClassId = ETEAEP_CLASS_ID_1,
            grossAnnualIncome = Money(80_000),
            grossDailyIncomes = emptyList(),
            annualExpensesAmount = Money(2_000)
        )
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
        val totalInsuranceCost = InsuranceCostCalculator(insuredPerson, efkaClasses, eteaepClasses).calculateYearlyInsuranceCost()

        assertThat(totalInsuranceCost).isEqualTo(Money(288 * 12))
    }
}
