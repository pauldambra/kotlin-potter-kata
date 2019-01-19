package com.dambra.paul.potter

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

internal class BasketsOfBooks {


    companion object {
        @Suppress("unused")
        @JvmStatic
        fun basketCases(): Stream<Arguments> = Stream.of(
                Arguments.of(
                        "one copy of a book costs 8 euros",
                        listOf(HarryPotterSet.BOOK_ONE),
                        Euros(800)),
                Arguments.of(
                        "two copies of the same book costs 16 euros",
                        listOf(HarryPotterSet.BOOK_ONE, HarryPotterSet.BOOK_ONE),
                        Euros(1600)),
                Arguments.of(
                        "three copies of the same book costs 24 euros",
                        listOf(HarryPotterSet.BOOK_ONE, HarryPotterSet.BOOK_ONE, HarryPotterSet.BOOK_ONE),
                        Euros(2400)),
                Arguments.of(
                        "four copies of the same book costs 32 euros",
                        listOf(HarryPotterSet.BOOK_ONE, HarryPotterSet.BOOK_ONE, HarryPotterSet.BOOK_ONE, HarryPotterSet.BOOK_ONE),
                        Euros(3200)),
                Arguments.of(
                        "five copies of the same book costs 40 euros",
                        listOf(HarryPotterSet.BOOK_ONE, HarryPotterSet.BOOK_ONE, HarryPotterSet.BOOK_ONE, HarryPotterSet.BOOK_ONE, HarryPotterSet.BOOK_ONE),
                        Euros(4000)),
                Arguments.of(
                        "two different books have 5% discount and cost 1520 euro cents",
                        listOf(HarryPotterSet.BOOK_ONE, HarryPotterSet.BOOK_TWO),
                        Euros(1520)),
                Arguments.of(
                        "three different books have 10% discount and cost 2160 euro cents",
                        listOf(HarryPotterSet.BOOK_ONE, HarryPotterSet.BOOK_TWO, HarryPotterSet.BOOK_THREE),
                        Euros(2160)),
                Arguments.of(
                        "four different books have 20% discount and cost 2560 euro cents",
                        listOf(HarryPotterSet.BOOK_ONE, HarryPotterSet.BOOK_TWO, HarryPotterSet.BOOK_THREE, HarryPotterSet.BOOK_FOUR),
                        Euros(2560)),
                Arguments.of(
                        "five different books have 25% discount and cost 3000 euro cents",
                        listOf(HarryPotterSet.BOOK_ONE, HarryPotterSet.BOOK_TWO, HarryPotterSet.BOOK_THREE, HarryPotterSet.BOOK_FOUR, HarryPotterSet.BOOK_FIVE),
                        Euros(3000)),
                Arguments.of(
                        "a basket that is one of the books and five different books has 25% discount on the complete set and no discount on the added book and cost 3800 euro cents",
                        listOf(HarryPotterSet.BOOK_ONE, HarryPotterSet.BOOK_TWO, HarryPotterSet.BOOK_THREE, HarryPotterSet.BOOK_FOUR, HarryPotterSet.BOOK_FIVE),
                        Euros(3000)),
                Arguments.of(
                        "baskets that have more than one combination should have the largest discount",
                        listOf(HarryPotterSet.BOOK_ONE, HarryPotterSet.BOOK_ONE, HarryPotterSet.BOOK_TWO, HarryPotterSet.BOOK_TWO, HarryPotterSet.BOOK_THREE, HarryPotterSet.BOOK_THREE, HarryPotterSet.BOOK_FOUR, HarryPotterSet.BOOK_FIVE),
                        Euros(5120))
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