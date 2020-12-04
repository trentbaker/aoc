import java.io.File
import java.math.BigInteger

val field = File("day3.input").readLines()

val right3Down1 = field.mapIndexed { i, row -> row[(i * 3) % row.length] }

println(right3Down1.count { it == '#' })

val right1Down1 = field.mapIndexed { i, row -> row[i % row.length] }
val right5Down1 = field.mapIndexed { i, row -> row[(i * 5) % row.length] }
val right7Down1 = field.mapIndexed { i, row -> row[(i * 7) % row.length] }
val right1Down2 = field.chunked(2).map { it.first() }.mapIndexed { i, row -> row[i % row.length] }

val partTwo = listOf(
    right1Down1,
    right3Down1,
    right5Down1,
    right7Down1,
    right1Down2,
).map { it.count { it == '#' } }

val product = partTwo.fold(BigInteger.ONE) { acc: BigInteger, i: Int -> acc * i.toBigInteger() }
println(product)
