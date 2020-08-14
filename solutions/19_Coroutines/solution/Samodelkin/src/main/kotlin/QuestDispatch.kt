import java.util.concurrent.atomic.AtomicInteger
import kotlinx.coroutines.*

private const val ADVENTURER_COUNT = 4000
private const val QUEST_REWARD_SIZE = 100

private var treasureCount = AtomicInteger(0)

fun main() {
    runBlocking {
        withContext(Dispatchers.Default) {
            dispatchAdventurers(ADVENTURER_COUNT) { bounty ->
                treasureCount.addAndGet(bounty)
            }
        }

        println("Total haul: \$$treasureCount")
    }
}

private suspend fun dispatchAdventurers(
    adventurerCount: Int,
    quest: suspend (Int) -> Unit
) {
    coroutineScope {
        repeat(adventurerCount) {
            launch {
                quest(QUEST_REWARD_SIZE)
            }
        }
    }
}
