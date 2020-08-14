package com.bignerdranch.nyethack

import java.io.File

const val tavernName: String = "Taernyl's Folly"

val patronList = mutableListOf("Eli", "Mordoc", "Sophie")
val uniquePatrons = mutableSetOf<String>()
val lastName = listOf("Ironfoot", "Fernsworth", "Baggins")
val menuList = File("data/tavern-menu-items.txt")
    .readText()
    .split("\n")
val patronGold = mutableMapOf<String, Double>()

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

    uniquePatrons.forEach {
        patronGold[it] = 6.0
    }

    var orderCount = 0
    while (orderCount <= 9) {
        placeOrder(
            uniquePatrons.random(),
            menuList.random()
        )
        orderCount++
    }

    displayPatronBalances()
}

private fun displayPatronBalances() {
    patronGold.forEach { (patron, balance) ->
        println("$patron, balance: ${"%.2f".format(balance)}")
    }
}

fun performPurchase(price: Double, patronName: String) {
    val totalPurse = patronGold.getValue(patronName)
    patronGold[patronName] = totalPurse - price
}

private fun placeOrder(patronName: String, menuData: String) {
    val indexOfApostrophe = tavernName.indexOf('\'')
    val tavernMaster = tavernName.substring(0 until indexOfApostrophe)
    println("$patronName speaks with $tavernMaster about their order.")

    val (type, name, price) = menuData.split(',')
    val message = "$patronName buys a $name ($type) for $price."
    println(message)

    performPurchase(price.toDouble(), patronName)

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
