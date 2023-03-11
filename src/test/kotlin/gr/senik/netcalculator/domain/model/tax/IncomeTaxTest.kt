//  package gr.senik.netcalculator.domain.model.tax
// TODO delete or uncomment
//import gr.alx.common.domain.model.Money
//import gr.senik.netcalculator.domain.model.v2.IncomeTax
//import gr.senik.netcalculator.domain.model.v2.IncomeTaxId
//import gr.senik.netcalculator.domain.model.v2.TaxLevel
//import org.assertj.core.api.Assertions.assertThat
//import org.junit.jupiter.api.BeforeEach
//import org.junit.jupiter.api.Test
//import java.util.*
//
//internal class IncomeTaxTest {
//    private lateinit var taxLevels: List<TaxLevel>
//
//    @BeforeEach
//    fun setUp() {
//        taxLevels = TaxTestHelper.incomeTaxLevels()
//    }
//
//    @Test
//    fun `should calculate total income tax amount with excess`() {
//
//        val incomeTax = IncomeTax(IncomeTaxId(UUID.randomUUID()), Money(80_000), taxLevels)
//
//        assertThat(incomeTax.totalTaxAmount).isEqualTo(Money(27_100.00))
//    }
//
//    @Test
//    fun `should calculate total income tax amount without excess`() {
//
//        val incomeTax = IncomeTax(Money(35_000), taxLevels)
//
//        assertThat(incomeTax.totalTaxAmount).isEqualTo(Money(7_700.00))
//    }
//}
