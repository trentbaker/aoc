import java.io.File

val input = File("day6.input").readText().split("\n\n")

val groups = input.map { it.split("\n") }

val uniqueYesAnswers = groups.map { it.flatMap { it.toList() }.distinct().size }

println(uniqueYesAnswers.sum())

val unanimousYesAnswers =
    groups.map { group ->
        group.flatMap { it.toList() }.filter { c ->
            group.all { it.contains(c) }
        }.distinct().size
    }

println(unanimousYesAnswers.sum())