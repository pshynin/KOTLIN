import kotlin.random.Random

fun main() {
    var swordsJuggling: Int? = null
    val isJugglingProficient = Random.nextInt(3) == 0
    if (isJugglingProficient) {
        swordsJuggling = 2
    }

    try {
        proficiencyCheck(swordsJuggling)
        swordsJuggling = swordsJuggling!!.plus(1)
    } catch (e: IllegalStateException) {
        println(e)
    }

    println("You juggle $swordsJuggling swords!")
}

fun proficiencyCheck(swordsJuggling: Int?) {
    checkNotNull(swordsJuggling, { "Player cannot juggle swords" })
}

class UnskilledSwordJugglerException() :
        IllegalStateException("Player cannot juggle swords")
