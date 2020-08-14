fun main() {
    val name = "Madrigal"
    val healthPoints = 100
    val isBlessed = true
    if (healthPoints == 100) {
        println(name + "is in excellent condition!")
    } else if (healthPoints >= 90) {
        println(name + " has a few scratches.")
    } else if (healthPoints >= 75) {
        if (isBlessed) {
            println(name + " has some minor wounds but is healing quite quickly!")
        } else {
            println(name + " has some minor wounds.")
        }
    } else if (healthPoints >= 15) {
        println(name + " looks pretty hurt.")
    } else {
        println(name + " is in awful condition!")
    }

    val healthSummary = if (healthPoints != 100) "Need healing!" else "Looking good."

    val healthStatus: String = when (healthPoints) {
        100 -> "is in excellent condition!"
        in 90..99 -> "has a few scratches."
        in 75..89 -> if (isBlessed) {
            "has some minor wounds but is healing quite quickly!"
        } else {
            "has some minor wounds."
        }
        in 15..74 -> "looks pretty hurt."
        else -> "is in awful condition!"
    }
}