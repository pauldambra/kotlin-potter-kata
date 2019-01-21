package com.dambra.paul.potter

class Basket {
    private val books: MutableList<Book> = mutableListOf()

    fun add(book: Book) {
        books += book
    }

    fun total(): Euros = Price.`for`(books) - Discount.`for`(books)
}