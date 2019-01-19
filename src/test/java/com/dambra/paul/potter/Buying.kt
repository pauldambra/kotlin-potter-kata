package com.dambra.paul.potter

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class Buying {
    @Test
    fun `one book costs 8 euros`() {
        val basket = Basket()
        basket.add(Book("The Philosopher's Stone"))
        assertThat(basket.total()).isEqualTo(Euros(8))
    }
}

data class Euros(val amount: Int)

data class Book(val title: String)

class Basket {
    private val books: MutableList<Book> = mutableListOf()

    fun add(book: Book) {
        books += book
    }

    fun total(): Euros = Euros(books.size * 8)
}
