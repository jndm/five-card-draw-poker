package game

enum class Wins(val str : String) {
    NOWIN(""),
    PAIR("Pair"),
    TWOPAIRS("Two-pairs"),
    THREEOFKIND( "Three-of-a-kind"),
    STRAIGHT("Straight"),
    FLUSH("Flush"),
    FULLHOUSE("Full-house"),
    FOUROFKIND("Four-of-a-kind"),
    STRAIGHTFLUSH("Straight-flush"),
    ROYALFLUSH("Royal-flush")
}

class WinTable(private val hand : List<Card>) {
    var win : Wins
        private set

    init {
        win = checkWin()
    }

    private fun isRoyalFlush() : Boolean = hand.firstOrNull { it.numericValue == 13 } != null && hand.firstOrNull { it.numericValue == 1} != null && isFlush() && isStraight()

    private fun isStraightFlush() : Boolean = isFlush() && isStraight()

    private fun isStraight() : Boolean {
        if(isOfKind(2)) {
            return false
        }

        val tmpHand = hand.toMutableList()
        if(tmpHand.any { it.numericValue == 13 } && tmpHand.any{ it.numericValue == 1 }) {
            tmpHand.remove(tmpHand.find { it.numericValue == 1 })
            tmpHand.add(Card(null, 14))
        }

        tmpHand.sortBy { it.numericValue }

        var isStraigth = true
        for(i in 0 until tmpHand.size - 1) {
            if((tmpHand[i].numericValue + 1) != tmpHand[i+1].numericValue) {
                isStraigth = false
                break
            }
        }

        return isStraigth
    }

    private fun isFlush() : Boolean = hand.distinctBy { it.suit }.size == 1

    private fun isOfKind(count : Int) : Boolean = hand.groupBy { it.numericValue }.any {entry -> entry.value.size >= count }

    private fun isFullHouse() : Boolean = hand.groupBy { it.numericValue }.any {entry -> entry.value.size == 2 }
            && hand.groupBy { it.numericValue }.any {entry -> entry.value.size == 3 }

    private fun isTwoPairs() : Boolean = hand.groupBy { it.numericValue }.filter { entry -> entry.value.size == 2 }.size == 2


    private fun checkWin() : Wins = when {
        isRoyalFlush() -> Wins.ROYALFLUSH
        isStraightFlush() -> Wins.STRAIGHTFLUSH
        isOfKind(4) -> Wins.FOUROFKIND
        isFullHouse() -> Wins.FULLHOUSE
        isFlush() -> Wins.FLUSH
        isStraight() -> Wins.STRAIGHT
        isOfKind(3) -> Wins.THREEOFKIND
        isTwoPairs() -> Wins.TWOPAIRS
        isOfKind(2) -> Wins.PAIR
        else -> Wins.NOWIN
    }

    override fun toString(): String {
        if(win == Wins.NOWIN) {
            return "Sorry, no win this time."
        } else {
            return "Congratulations! You got ${win.str}"
        }
    }
}