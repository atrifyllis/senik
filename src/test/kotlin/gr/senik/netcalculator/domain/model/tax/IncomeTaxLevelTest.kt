package gr.senik.netcalculator.domain.model.tax

import gr.alx.common.domain.model.Money
import gr.senik.netcalculator.domain.model.TaxDataHelper
import gr.senik.netcalculator.domain.model.TaxLevel
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

internal class IncomeTaxLevelTest {

    private lateinit var incomeTaxLevel: TaxLevel

    @BeforeEach
    fun setUp() {
        incomeTaxLevel = TaxDataHelper.incomeTaxLevels().first()
    }

    @Test
    fun `should calculate tax for first level with exactly the level amount`() {

        val taxAmount = incomeTaxLevel.calculateLevelAmount(Money(10_000))

        assertThat(taxAmount).isEqualTo(Money(900.00))
    }

    @Test
    fun `should calculate tax for first level with less than the level amount`() {
        val taxAmount = incomeTaxLevel.calculateLevelAmount(Money(5_000))

        assertThat(taxAmount).isEqualTo(Money(450.00))
    }

    @Test
    fun `should calculate tax for first level with more than the level amount`() {
        val taxAmount = incomeTaxLevel.calculateLevelAmount(Money(12_550))

        assertThat(taxAmount).isEqualTo(Money(900.00))
    }

    @Test
    fun `should calculate tax for zero amount`() {
        val taxAmount = incomeTaxLevel.calculateLevelAmount(Money.ZERO)

        assertThat(taxAmount).isEqualTo(Money(0.00))
    }
}
