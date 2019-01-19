package com.dambra.paul.potter

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class Buying {
    @Test
    fun `one book costs 8 euros`() {
        val basket = Basket()
        basket.add(Book("The Philosopher's Stone"))
        assertThat(basket.total()).isEqualTo(Euros(800))
    }

    @Test
    fun `two books costs 16 euros`() {
        val basket = Basket()
        basket.add(Book("The Philosopher's Stone"))
        basket.add(Book("The Philosopher's Stone"))
        assertThat(basket.total()).isEqualTo(Euros(1600))
    }
}

data class Euros(val cents: Int)

data class Book(val title: String)

class Price {
    companion object {
        fun `for`(books: List<Book>) = Euros(books.size * 800)
    }
}

class Basket {
    private val books: MutableList<Book> = mutableListOf()

    fun add(book: Book) {
        books += book
    }

    fun total(): Euros = Price.`for`(books)
}
