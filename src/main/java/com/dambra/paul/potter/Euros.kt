package com.dambra.paul.potter

import java.text.NumberFormat
import java.util.*

data class Euros(val cents: Int) {

    operator fun minus(other: Euros) = Euros(cents - other.cents)
    operator fun plus(other: Euros) = Euros(cents + other.cents)

    override fun toString(): String = euro.format(cents / 100.0)

    companion object {
        private val euro = NumberFormat.getCurrencyInstance(Locale.FRANCE)
    }
}