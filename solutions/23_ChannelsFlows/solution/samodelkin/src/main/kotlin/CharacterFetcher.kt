import java.net.URL
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.produce

const val ENDPOINT = "http://0.0.0.0:8080/"
const val TEN_SECONDS_IN_MILLISECONDS: Long = 10000

fun main() {
    runBlocking {
        println("Started")
        launch {
            val characterChannel = pollForCharacters()
            repeat(10) {
                println(characterChannel.receive())
            }
        }
        println("Finished")
    }
}

@ExperimentalCoroutinesApi
suspend fun CoroutineScope.pollForCharacters(): ReceiveChannel<String> =
        produce {
            while (true) {
                val character = fetchCharacter()
                send(character)
                delay(TEN_SECONDS_IN_MILLISECONDS)
            }
        }

suspend fun fetchCharacter(): String = withContext(Dispatchers.IO) {
    URL(ENDPOINT).readText()
}
