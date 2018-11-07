package test

import game.Card
import game.Suit
import game.WinTable
import game.Wins
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class WinTableTest {

    @Test
    fun testNoWin() {
        val hand = listOf(Card(Suit.CLUB, 11),
            Card(Suit.HEART, 2),
            Card(Suit.CLUB, 6),
            Card(Suit.CLUB, 7),
            Card(Suit.CLUB, 8))

        val wintable = WinTable(hand)

        Assertions.assertEquals(Wins.NOWIN, wintable.win)
    }

    @Test
    fun testPair() {
        val hand = listOf(Card(Suit.CLUB, 2),
            Card(Suit.HEART, 2),
            Card(Suit.CLUB, 6),
            Card(Suit.CLUB, 7),
            Card(Suit.CLUB, 8))

        val wintable = WinTable(hand)

        Assertions.assertEquals(Wins.PAIR, wintable.win)
    }

    @Test
    fun testTwoPairs() {
        val hand = listOf(Card(Suit.CLUB, 2),
            Card(Suit.HEART, 2),
            Card(Suit.CLUB, 6),
            Card(Suit.CLUB, 7),
            Card(Suit.DIAMOND, 6))

        val wintable = WinTable(hand)

        Assertions.assertEquals(Wins.TWOPAIRS, wintable.win)
    }

    @Test
    fun testThreeOfKind() {
        val hand = listOf(Card(Suit.CLUB, 2),
            Card(Suit.HEART, 2),
            Card(Suit.CLUB, 6),
            Card(Suit.DIAMOND, 2),
            Card(Suit.CLUB, 8))

        val wintable = WinTable(hand)

        Assertions.assertEquals(Wins.THREEOFKIND, wintable.win)
    }

    @Test
    fun testHighStraight() {
        val hand = listOf(Card(Suit.DIAMOND, 1),
            Card(Suit.HEART, 10),
            Card(Suit.SPADE, 11),
            Card(Suit.CLUB, 12),
            Card(Suit.CLUB, 13))

        val wintable = WinTable(hand)

        Assertions.assertEquals(Wins.STRAIGHT, wintable.win)
    }

    @Test
    fun testLowStraight() {
        val hand = listOf(Card(Suit.DIAMOND, 1),
            Card(Suit.HEART, 2),
            Card(Suit.SPADE, 3),
            Card(Suit.CLUB, 4),
            Card(Suit.CLUB, 5))

        val wintable = WinTable(hand)

        Assertions.assertEquals(Wins.STRAIGHT, wintable.win)
    }

    @Test
    fun testStraight() {
        val hand = listOf(Card(Suit.DIAMOND, 6),
            Card(Suit.HEART, 7),
            Card(Suit.SPADE, 8),
            Card(Suit.CLUB, 9),
            Card(Suit.CLUB, 10))

        val wintable = WinTable(hand)

        Assertions.assertEquals(Wins.STRAIGHT, wintable.win)
    }

    @Test
    fun testFlush() {
        val hand = listOf(Card(Suit.CLUB, 12),
            Card(Suit.CLUB, 2),
            Card(Suit.CLUB, 6),
            Card(Suit.CLUB, 7),
            Card(Suit.CLUB, 8))

        val wintable = WinTable(hand)

        Assertions.assertEquals(Wins.FLUSH, wintable.win)
    }

    @Test
    fun testFullhouse() {
        val hand = listOf(Card(Suit.CLUB, 2),
            Card(Suit.HEART, 2),
            Card(Suit.DIAMOND, 10),
            Card(Suit.HEART, 10),
            Card(Suit.SPADE, 10))

        val wintable = WinTable(hand)

        Assertions.assertEquals(Wins.FULLHOUSE, wintable.win)
    }

    @Test
    fun testFourOfKind() {
        val hand = listOf(Card(Suit.CLUB, 2),
            Card(Suit.HEART, 2),
            Card(Suit.SPADE, 2),
            Card(Suit.DIAMOND, 2),
            Card(Suit.CLUB, 8))

        val wintable = WinTable(hand)

        Assertions.assertEquals(Wins.FOUROFKIND, wintable.win)
    }

    @Test
    fun testLowStraightFlush() {
        val hand = listOf(Card(Suit.CLUB, 1),
            Card(Suit.CLUB, 2),
            Card(Suit.CLUB, 3),
            Card(Suit.CLUB, 4),
            Card(Suit.CLUB, 5))

        val wintable = WinTable(hand)

        Assertions.assertEquals(Wins.STRAIGHTFLUSH, wintable.win)
    }

    @Test
    fun testStraightFlush() {
        val hand = listOf(Card(Suit.CLUB, 6),
            Card(Suit.CLUB, 7),
            Card(Suit.CLUB, 8),
            Card(Suit.CLUB, 9),
            Card(Suit.CLUB, 10))

        val wintable = WinTable(hand)

        Assertions.assertEquals(Wins.STRAIGHTFLUSH, wintable.win)
    }

    @Test
    fun testHighStraightFlush() {
        val hand = listOf(Card(Suit.CLUB, 1),
            Card(Suit.CLUB, 10),
            Card(Suit.CLUB, 11),
            Card(Suit.CLUB, 12),
            Card(Suit.CLUB, 13))

        val wintable = WinTable(hand)

        Assertions.assertNotEquals(Wins.STRAIGHTFLUSH, wintable.win)
        Assertions.assertEquals(Wins.ROYALFLUSH, wintable.win)
    }
}