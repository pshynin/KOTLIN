import java.io.File
import kotlin.math.roundToInt

const val tavernName: String = "Taernyl's Folly"

var playerGold = 10
var playerSilver = 10
val patronList = mutableListOf("Eli", "Mordoc", "Sophie")
val uniquePatrons = mutableSetOf<String>()
val lastName = listOf("Ironfoot", "Fernsworth", "Baggins")
val menuList = File("data/tavern-menu-items.txt")
    .readText()
    .split("\n")

fun main() {
    val eliMessage = if (patronList.contains("Eli")) {
        "The tavern master says: Eli's in the back playing cards."
    } else {
        "The tavern master says: Eli isn't here."
    }
    println(eliMessage)

    val othersMessage = if (patronList.containsAll(listOf("Sophie", "Mordoc"))) {
        "The tavern master says: Yea, they're seated by the stew kettle."
    } else {
        "The tavern master says: Nay, they departed hours ago."
    }
    println(othersMessage)

    repeat(10) {
        val first = patronList.random()
        val last = lastName.random()
        val name = "$first $last"
        uniquePatrons += name
    }
    println(uniquePatrons)

    var patronCount = 0
    while (patronCount <= 9) {
        placeOrder(
            uniquePatrons.random(),
            menuList.random()
        )
        patronCount++
    }
}

fun performPurchase(price: Double) {
    displayBalance()
    val totalPurse = playerGold + (playerSilver / 100.0)
    println("Total purse: $totalPurse")
    println("Purchasing item for $price")

    val remainingBalance = totalPurse - price
    println("Remaining balance: ${"%.2f".format(remainingBalance)}")

    val remainingGold = remainingBalance.toInt()
    val remainingSilver = (remainingBalance % 1 * 100).roundToInt()
    playerGold = remainingGold
    playerSilver = remainingSilver
    displayBalance()
}

private fun displayBalance() {
    println("Player's purse balance: Gold: $playerGold , Silver: $playerSilver")
}

private fun placeOrder(patronName: String, menuData: String) {
    val indexOfApostrophe = tavernName.indexOf('\'')
    val tavernMaster = tavernName.substring(0 until indexOfApostrophe)
    println("$patronName speaks with $tavernMaster about their order.")

    val (type, name, price) = menuData.split(',')
    val message = "$patronName buys a $name ($type) for $price."
    println(message)

//    performPurchase(price.toDouble())

    val phrase: String = if (name == "Dragon's Breath") {
        "$patronName exclaims: ${toDragonSpeak("Ah, delicious $name!")}"
    } else {
        "$patronName says: Thanks for the $name."
    }
    println(phrase)
}

private fun toDragonSpeak(phrase: String): String =
    phrase.replace(Regex("[aeiou]")) {
        when (it.value) {
            "a" -> "4"
            "e" -> "3"
            "i" -> "1"
            "o" -> "0"
            "u" -> "|_|"
            else -> it.value
        }
    }
