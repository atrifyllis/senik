package gr.senik.netcalculator.domain.model.tax

import gr.senik.common.domain.model.Money
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

internal class IncomeTaxTest {
    private lateinit var taxLevels: List<IncomeTaxLevel>

    @BeforeEach
    fun setUp() {
        taxLevels = TaxTestHelper.incomeTaxLevels()
    }

    @Test
    fun `should calculate total income tax amount with excess`() {

        val incomeTax = IncomeTax(Money(80_000), taxLevels)

        assertThat(incomeTax.totalTaxAmount).isEqualTo(Money(27_100.00))
    }

    @Test
    fun `should calculate total income tax amount without excess`() {

        val incomeTax = IncomeTax(Money(35_000), taxLevels)

        assertThat(incomeTax.totalTaxAmount).isEqualTo(Money(7_700.00))
    }
}
