package com.dambra.paul.potter

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.text.NumberFormat
import java.util.*
import java.util.stream.Stream

internal class BasketsOfBooks {


    companion object {
        @Suppress("unused")
        @JvmStatic
        fun basketCases(): Stream<Arguments> = Stream.of(
                Arguments.of(
                        "one copy of a book costs 8 euros",
                        listOf("The Philosopher's Stone"),
                        Euros(800)),
                Arguments.of(
                        "two copies of the same book costs 16 euros",
                        listOf("The Philosopher's Stone", "The Philosopher's Stone"),
                        Euros(1600)),
                Arguments.of(
                        "three copies of the same book costs 24 euros",
                        listOf("The Philosopher's Stone", "The Philosopher's Stone", "The Philosopher's Stone"),
                        Euros(2400)),
                Arguments.of(
                        "four copies of the same book costs 32 euros",
                        listOf("The Philosopher's Stone", "The Philosopher's Stone", "The Philosopher's Stone", "The Philosopher's Stone"),
                        Euros(3200)),
                Arguments.of(
                        "five copies of the same book costs 40 euros",
                        listOf("The Philosopher's Stone", "The Philosopher's Stone", "The Philosopher's Stone", "The Philosopher's Stone", "The Philosopher's Stone"),
                        Euros(4000)),
                Arguments.of(
                        "two different books have 5% discount and cost 1520 euro cents",
                        listOf("The Philosopher's Stone", "The Chamber of Secrets"),
                        Euros(1520)),
                Arguments.of(
                        "three different books have 10% discount and cost 2160 euro cents",
                        listOf("The Philosopher's Stone", "The Chamber of Secrets", "The Prisoner of Azkaban"),
                        Euros(2160)),
                Arguments.of(
                        "four different books have 20% discount and cost 2560 euro cents",
                        listOf("The Philosopher's Stone", "The Chamber of Secrets", "The Prisoner of Azkaban", "The Goblet of Fire"),
                        Euros(2560)),
                Arguments.of(
                        "five different books have 25% discount and cost 3000 euro cents",
                        listOf("The Philosopher's Stone", "The Chamber of Secrets", "The Prisoner of Azkaban", "The Goblet of Fire", "The Order of the Phoenix"),
                        Euros(3000))
        )
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("basketCases")
    fun whenCostIsCalculated(testcaseName: String, bookTitles: List<String>, expectedCost: Euros) {
        val basket = Basket()

        bookTitles.map(::Book)
                .forEach(basket::add)

        assertThat(basket.total())
                .isEqualTo(expectedCost)
    }
}

data class Euros(val cents: Int) {

    operator fun minus(other:Euros) = Euros(cents - other.cents)

    override fun toString(): String = euro.format(cents/100.0)

    companion object {
        private val euro = NumberFormat.getCurrencyInstance(Locale.FRANCE)
    }
}

data class Book(val title: String)

class Price {
    companion object {
        fun `for`(books: List<Book>) = Euros(books.size * 800)
    }
}

class Discount {
    companion object {
        fun `for`(books: List<Book>): Euros {
            return when {
                books.size == 2 && books.size == books.distinct().size -> Euros(80)
                books.size == 3 && books.size == books.distinct().size -> Euros(240)
                books.size == 4 && books.size == books.distinct().size -> Euros(640)
                books.size == 5 && books.size == books.distinct().size -> Euros(1000)
                else -> Euros(0)
            }
        }
    }
}

class Basket {
    private val books: MutableList<Book> = mutableListOf()

    fun add(book: Book) {
        books += book
    }

    fun total(): Euros = Price.`for`(books) - Discount.`for`(books)
}
