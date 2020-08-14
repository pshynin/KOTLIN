import java.net.URL
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

const val ENDPOINT = "http://0.0.0.0:8080/"

fun main() {
    runBlocking {
        println("Started")
        launch {
            val character = fetchCharacter()
            println(character)
        }
        println("Finished")
    }
}

suspend fun fetchCharacter(): String = withContext(Dispatchers.IO) {
    URL(ENDPOINT).readText()
}
