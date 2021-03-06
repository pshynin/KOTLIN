package com.bignerdranch.nyethack

fun main() {
    val player = Player("Madrigal")
    player.castFireball()

    // Player status
    printPlayerStatus(player)
}

private fun printPlayerStatus(player: Player) {
    println("(Aura: ${player.auraColor()}) " +
            "(Blessed: ${if (player.isBlessed) "YES" else "NO"})")
    println("${player.name} ${player.formatHealthStatus()}")
}
