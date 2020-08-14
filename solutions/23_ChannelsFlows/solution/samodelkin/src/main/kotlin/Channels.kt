import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() {
    val numberChannel = Channel<Int>()

    runBlocking {
        launch {
            for (i in 0..9) {
                numberChannel.send(i)
            }
            numberChannel.close()
        }

        launch {
            for (number in numberChannel) {
                println(number)
            }
        }
    }
}
