import java.io.File

fun main() {
    var signatureDrink = "Buttered Ale"

    val menuFile = File("menu-file.txt")
    menuFile.setReadable(true)
    menuFile.setWritable(true)
    menuFile.setExecutable(false)
}

