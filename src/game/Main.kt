package game

fun main(args: Array<String>) {
    val inputPattern = Regex("""(^n$|^([1-5]\s){1,5}[1-5]?$|^[1-5]${'$'})""")
    var playNewRound = true
    while(playNewRound) {
        val deck = Deck()
        var hand = deck.serve(5)
        println(hand.withIndex().joinToString(separator = " | ") { "${it.index + 1 }. ${it.value}"})

        println("Choose cards to discard. [1-5]")
        var input = readLine()
        while (input == null || input?.length == 0 || !input?.matches(inputPattern)) {
            println("Invalid input. Please input only cards [1-5] (separated with whitespace in case changing many cards. (Eg. 1 2 3)")
            input = readLine()
        }

        val discards = input.split(" ").map { it.trim().toInt() - 1 }

        discards.forEach { hand[it] = deck.getCard() }

        println(hand.withIndex().joinToString(separator = " | ") { "${it.index + 1 }. ${it.value}"})
        println(WinTable(hand))

        println("Want to play again? (y/n)")
        input = readLine()
        while (input == null || (input.toLowerCase() != "y" && input.toLowerCase() != "n")) {
            println("(y/n)?")
            input = readLine()
        }

        if(input.toLowerCase() == "n") {
            playNewRound = false
        }
    }
}
