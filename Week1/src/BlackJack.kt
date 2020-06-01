/**
 * Assignment 1
 *
 * Program that creates a deck of cards,
 * deals two cards from that deck into a hand
 * and evaluates that hand of cards by finding
 * the sum of the pips. Display the cards in the
 * hand and the total of the pips in the hand.
 *
 * @author Emmanuel Cruz (em4n0101@gmail.com)
 */
fun main() {
    // Get the deck and the hand with 2 cards
    var deck = createDeck()
    var hand = dealHand(deck, 2).toMutableList()

    // Print the cards
    println("\n\nYour hand was:")
    println(getVisualRepresentationFor(hand.toList()))

    // Check if get 21 or more, if so game it's over, if not ask the user for another card
    var total = evaluateHand(hand)
    if (total >= 21)
        printResults(total)
    else {
        println("Add additional card? (Y/N)")
        val input = readLine()
        if (input?.toLowerCase() == "Y".toLowerCase()) {
            // Get additional card and evaluate it
            hand.addAll(dealHand(deck, 1))
            total = evaluateHand(hand)
            val lastCard = hand.last()
            println(getVisualRepresentationFor(listOf(lastCard)))

            printResults(total)

        } else {
            printResults(total)
        }
    }
}

/**
 * Function that creates a collection of cards
 *
 * @return the collection of cards
 */
fun createDeck(): MutableSet<Card> {
    val suits = setOf('\u2663', '\u2660', '\u2666', '\u2665')
    val pips = 1..13

    var deck = mutableSetOf<Card>()

    for (pip in pips) {
        for (suit in suits) {
            deck.add(Card(pip, suit))
        }
    }

    return deck
}

/**
 * Function that can deal n cards from the deck
 *
 * @param deck the collection of cards
 * @param numberOfCards number of cards it should place in the hand
 * @return collection containing the number of cards specified
 */
fun dealHand(deck: MutableSet<Card>, numberOfCards: Int) : List<Card> {
    var cards = mutableListOf<Card>()

    for (i in 0 until numberOfCards) {
        val randomCard = deck.random()
        deck.remove(randomCard)
        cards.add(randomCard)
    }
    return cards
}

/**
 * Function that evaluate the hand received
 *
 * @param hand the hand to evaluate
 * @return the total score
 */
fun evaluateHand(hand: List<Card>): Int {
    var res = 0
    for (card in hand) {
        res += card.getValueForPips()
    }
    return res
}

/**
 * Function that print the total of the hand
 *
 * @param total the total of the hand
 */
fun printResults(total: Int) {
    println("For a total of: $total")
    if (total == 21)
        println("You Win!")
    else if (total >= 22)
        println("You Lose!")
}

/**
 * Function that get the visual representation of a hand
 *
 * @param cards the cards in the hand
 * @return a message with the visual representation of a card
 */
fun getVisualRepresentationFor(cards: List<Card>) : String {
    when (cards.size) {
        1 -> {
            val card = cards.first()

            return """ 
          .------.                                        
          |${card.getReadablePips()}      |      
          |       |                                        
          |   ${card.suit}   |        
          |       |
          |      ${card.getReadablePips()}|                                        
          `-------'""".trimIndent()
        }
        2 -> {
            val firstCard = cards.first()
            val secondCard = cards.last()

            return """
    .------.                                              
    |${firstCard.getReadablePips()}     |
    |     .------.                                        
    |   ${firstCard.suit} |${secondCard.getReadablePips()}      |      
    |     |       |                                        
    |     |   ${secondCard.suit}   |        
    `-----|       |
          |      ${secondCard.getReadablePips()}|                                        
          `-------'""".trimIndent()
        }
        else -> {
            return ""
        }
    }
}

/**
 * Class to represent a card into the game
 */
class Card(private val pip: Int, val suit: Char) {
    /**
     * Function to get a simple description of a card (A♣)
     */
    override fun toString(): String {
        return "${getReadablePips()} $suit"
    }

    /**
     * Function to get a simple description of the pips (A, 1, 2, J, etc)
     */
    fun getReadablePips () : String {
        return when (pip) {
            1 -> "A"
            2, 3, 4, 5, 6, 7, 8, 9, 10 -> pip.toString()
            11 -> "J"
            12 -> "Q"
            13 -> "K"
            else -> ""
        }
    }

    /**
     * Function to get the value for the pip
     */
    fun getValueForPips() : Int {
        return when (pip) {
            1 -> 11
            2, 3, 4, 5, 6, 7, 8, 9, 10 -> pip
            11, 12, 13 -> 10
            else -> 0
        }
    }
}
