package gr.fnik.tax.domain.model

import gr.fnik.common.domain.model.Money
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class IncomeTaxLevelTest {

    @Test
    fun `should calculate tax for first level with exactly the level amount`() {
        val incomeTaxLevel = IncomeTaxLevel(TaxLevelType.FIRST_10K, Money(10_000), 0.09)

        val taxAmount = incomeTaxLevel.calculateLevelAmount(Money(10_000))

        assertThat(taxAmount).isEqualTo(Money(900.00))
    }

    @Test
    fun `should calculate tax for first level with less than the level amount`() {
        val incomeTaxLevel = IncomeTaxLevel(TaxLevelType.FIRST_10K, Money(10_000), 0.09)

        val taxAmount = incomeTaxLevel.calculateLevelAmount(Money(5_000))

        assertThat(taxAmount).isEqualTo(Money(450.00))
    }

    @Test
    fun `should calculate tax for first level with more than the level amount`() {
        val incomeTaxLevel = IncomeTaxLevel(TaxLevelType.FIRST_10K, Money(10_000), 0.09)

        val taxAmount = incomeTaxLevel.calculateLevelAmount(Money(12_550))

        assertThat(taxAmount).isEqualTo(Money(900.00))
    }

    @Test
    fun `should calculate tax for zero amount`() {
        val incomeTaxLevel = IncomeTaxLevel(TaxLevelType.FIRST_10K, Money(10_000), 0.09)

        val taxAmount = incomeTaxLevel.calculateLevelAmount(Money(0))

        assertThat(taxAmount).isEqualTo(Money(0.00))
    }
}
