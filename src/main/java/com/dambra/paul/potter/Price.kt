package com.dambra.paul.potter

class Price {
    companion object {
        fun `for`(books: List<Book>) = Euros(books.size * 800)
    }
}