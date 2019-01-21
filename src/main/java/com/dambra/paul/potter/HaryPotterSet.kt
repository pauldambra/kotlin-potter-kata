package com.dambra.paul.potter

data class Book(val title: String)

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

