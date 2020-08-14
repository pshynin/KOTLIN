import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.runBlocking

@ExperimentalCoroutinesApi
fun main() {
    listOf<String>().asFlow()
    runBlocking {
        primesFlow
            .filter { it.isPrime() }
            .take(1000)
            .filterIfBelowFreezing()
            .collect { println(it) }
    }
}

val primesFlow: Flow<Int> = flow {
    var number = 3
    while (true) {
        emit(number)
        number++
    }
}

const val FREEZING_POINT_KELVIN = 273.15

@ExperimentalCoroutinesApi
fun Flow<Int>.filterIfBelowFreezing(): Flow<Int> =
    transform { temperature ->
        if (temperature <= FREEZING_POINT_KELVIN) {
            emit(temperature)
        }
    }

val oneThousandPrimes = generateSequence(3) { value -> value + 1 }
    .filter { it.isPrime() }
    .take(1000)

// Extension to Int that determines whether a number is prime
fun Int.isPrime(): Boolean {
    (2 until this)
        .map { divisor ->
            if (this % divisor == 0) {
                return false // Not a prime!
            }
        }
    return true
}
