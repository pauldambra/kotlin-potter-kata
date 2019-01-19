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

class HarryPotterSet {
    private val books = mutableMapOf(
            BOOK_ONE to false,
            BOOK_TWO to false,
            BOOK_THREE to false,
            BOOK_FOUR to false,
            BOOK_FIVE to false
    )

    fun needs(book: Book) = !books[book.title]!!

    fun add(book: Book) {
        books[book.title] = true
    }

    fun discount() = when (numberOfBooksInSet()) {
        2 -> Euros(80)
        3 -> Euros(240)
        4 -> Euros(640)
        5 -> Euros(1000)
        else -> Euros(0)
    }

    fun numberOfBooksInSet() = books.count { it.value }

    companion object {
        const val BOOK_ONE = "The Philosopher's Stone"
        const val BOOK_TWO = "The Chamber of Secrets"
        const val BOOK_THREE = "The Prisoner of Azkaban"
        const val BOOK_FOUR = "The Goblet of Fire"
        const val BOOK_FIVE = "The Order of the Phoenix"
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
        fun `for`(books: List<Book>) =
                books
                        .fold(mutableListOf(HarryPotterSet())) { potterSets, book ->
                            val potterSetsWithoutThisBook = potterSets.filter { it.needs(book) }
                            if (potterSetsWithoutThisBook.isEmpty()) {
                                val x = HarryPotterSet()
                                x.add(book)
                                potterSets.add(x)
                            } else {
                                potterSetsWithoutThisBook.minBy { it.numberOfBooksInSet() }!!.add(book)
                            }

                            potterSets
                        }
                        .map(HarryPotterSet::discount)
                        .fold(Euros(0)) { total, next ->
                            total + next
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
