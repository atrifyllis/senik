package gr.senik.common.domain.model

import org.jmolecules.ddd.annotation.ValueObject
import java.math.BigDecimal
import java.math.RoundingMode
import java.util.*
import jakarta.persistence.Embeddable

private const val EUR = "EUR"

@Embeddable
@ValueObject
class Money(
    var amount: BigDecimal,
    var currencyCode: String,
) {
    companion object {
        val DEFAULT_CURRENCY: Currency = Currency.getInstance(EUR)
        val DEFAULT_ROUNDING_MODE: RoundingMode = RoundingMode.HALF_EVEN
        val ZERO = Money(0)
    }

    init {
        this.amount = amount.setScale(2, DEFAULT_ROUNDING_MODE)
    }

    constructor(amount: Double) : this(BigDecimal(amount), DEFAULT_CURRENCY.currencyCode)
    constructor(amount: Int) : this(BigDecimal(amount), DEFAULT_CURRENCY.currencyCode)
    constructor(amount: BigDecimal) : this(amount, DEFAULT_CURRENCY.currencyCode)

    operator fun plus(money: Money): Money {
        return Money(amount + money.amount)
    }

    operator fun minus(money: Money): Money {
        return Money(amount - money.amount)
    }

    operator fun times(money: Money): Money {
        return Money(amount * money.amount)
    }

    operator fun times(factor: Double): Money {
        return Money(amount * BigDecimal(factor))
    }

    operator fun times(number: Int): Money {
        return Money(amount * BigDecimal(number))
    }


    operator fun div(money: Money): Money {
        return Money(amount / money.amount)
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Money

        if (amount != other.amount) return false
        if (currencyCode != other.currencyCode) return false

        return true
    }


    operator fun compareTo(amount: Money): Int {
        return this.amount.compareTo(amount.amount)
    }

    override fun hashCode(): Int {
        var result = amount.hashCode()
        result = 31 * result + currencyCode.hashCode()
        return result
    }

    override fun toString(): String {
        return "Money(amount=$amount, currencyCode='$currencyCode')"
    }

    operator fun div(i: Int): Money {
        return Money(this.amount.divide(BigDecimal(i), DEFAULT_ROUNDING_MODE))
    }
}
