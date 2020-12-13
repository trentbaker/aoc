import java.io.File
import java.util.function.BiFunction

val input = File("day7.input").readLines()

data class Bag(
    val color: String,
    val contained: MutableMap<String, Int>, // color -> quantity
    val lazy: MutableList<String> // lazy ignoring quantities for now
) {
    override fun toString(): String = """
        ---
        | $color
        | $lazy
        ---
    """.trimIndent()
}

fun String.parseColors(): MutableList<String> {
    val words = split(' ')
    val bags = words.mapIndexedNotNull { i, word -> if (word.matches(Regex("bag.*"))) i else null }
    return bags.map { words.slice(it - 2..it - 1).joinToString(" ") }.filter { it != "no other" }.toMutableList()
}

fun String.parseBag(): Bag {
    val colors = parseColors()
    val counts = split(' ').mapNotNull { it.toIntOrNull() }
    val thisBagColor = colors.first()
    val containedBagColor = colors.drop(1).zip(counts).toMap().toMutableMap()
    return Bag(thisBagColor, containedBagColor, colors.drop(1).toMutableList())
}

val allBags = input.map { it.parseBag() }

fun List<Bag>.getWithColor(input: String) = first { it.color == input }

fun String.containsColorBag(target: String) = allBags.getWithColor(this).lazy.let {
    generateSequence(it) { previous ->
        val new = previous.flatMap { allBags.getWithColor(it).lazy }
        when {
            new.isEmpty() -> null
            previous.contains(target) -> null
            else -> new.toMutableList()
        }
    }.last().contains(target)
}

val allColors = input
    .flatMap { it.parseColors() }.distinct()

println(allColors.count { it.containsColorBag("shiny gold") })