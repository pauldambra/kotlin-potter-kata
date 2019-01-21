package com.dambra.paul.potter

class Discount {
    companion object {
        fun `for`(books: List<Book>) =
                books.fold(listOf(HarryPotterSet()), ::addBookToSmallestValidSet)
                        .map(HarryPotterSet::discount)
                        .fold(Euros(0)) { total, next ->
                            total + next
                        }

        private fun addBookToSmallestValidSet(potterSets: List<HarryPotterSet>, book: Book): List<HarryPotterSet> {
            val potterSetsWithoutThisBook = potterSets.filter { it.needs(book) }

            if (potterSetsWithoutThisBook.any()) {
                potterSetsWithoutThisBook.minBy { it.numberOfBooksInSet() }!!.add(book)
                return potterSetsWithoutThisBook
            }

            val x = HarryPotterSet()
            x.add(book)
            return potterSets + x
        }
    }
}