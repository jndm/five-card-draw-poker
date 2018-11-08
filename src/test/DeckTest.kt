package test

import game.Card
import game.Deck
import game.Suit
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import java.lang.IllegalArgumentException

class DeckTest {
    @Test
    fun allCardsInDeckAndOnlyOnce() {
        val deck = Deck()

        Assertions.assertTrue(deck.deck.groupBy { it.suit }.all { entry -> entry.value.size == 13 })
        Assertions.assertTrue(deck.deck.groupBy { it.numericValue }.all { entry -> entry.value.size == 4 })

        val groupedDeck = deck.deck.groupBy { it.suit }

        try {
            for(i in 1..13) {
                groupedDeck.values.forEach {
                    it.single { card -> card.numericValue == i}
                }
            }
            Assertions.assertTrue(true)
        } catch (e : IllegalArgumentException) {
            Assertions.fail<String>("Deck contains more than one same card!")
        }
    }
}