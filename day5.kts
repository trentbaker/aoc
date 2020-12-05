import java.io.File
import kotlin.math.pow

val input = File("day5.input").readLines()

fun String.binaryDecode() = reversed().mapIndexed { i, c ->
    when (c) {
        'R', 'B' -> 2.0.pow(i).toInt()
        else -> 0
    }
}.sum()

fun String.toSeatID() = (dropLast(3) to takeLast(3)).let { (rawSeatRow, rawSeatColumn) ->
    val seatRow = rawSeatRow.binaryDecode()
    val seatColumn = rawSeatColumn.binaryDecode()
    (seatRow * 8) + seatColumn // return the seat ID
}

val allSeatIDs = input.map { it.toSeatID() }.sorted()

println(allSeatIDs.maxOrNull())

val deltas: List<Pair<Int, Int?>> = allSeatIDs.mapIndexed { index, i ->
    i to allSeatIDs.getOrNull(index + 1)?.let { it - i }
}.filter { it.second != null }

val mySeat = deltas.first { it.second == 2 }.first // find the seat missing the next seat
    + 1 // and add one to get my seat

println(mySeat)
