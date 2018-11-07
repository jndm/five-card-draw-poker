package game

import java.util.*


enum class Suit(val shortened : Char) {
    HEART('\u2665'), DIAMOND('\u2666'), CLUB('\u2663'), SPADE('\u2660')
}

data class Card(val suit : Suit?, val numericValue : Int) {
    private val value : String = when (numericValue) {
        1 -> "A"
        in 2..10 -> numericValue.toString()
        11 -> "J"
        12 -> "Q"
        13 -> "K"
        else -> "???"
    }

    override fun toString() : String = "$value${suit?.shortened}"
}

class Deck {
    val deck : Stack<Card>

    init {
        deck = createDeck()
    }

    private fun createDeck() : Stack<Card> {
        val stack = Stack<Card>()
        val cards = mutableListOf<Card>()
        for(suit in Suit.values()) {
            for(j in 1..13) {
                cards.add(Card(suit, j))
            }
        }

        stack.addAll(cards.shuffled())
        return stack
    }

    fun serve(count : Int) : MutableList<Card> {
        val cards = mutableListOf<Card>()
        for (i in 0 until count) {
            cards.add(deck.pop())
        }
        return cards
    }

    fun getCard() : Card = deck.pop()
}