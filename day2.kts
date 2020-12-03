import java.io.File

val passwordsAndPolicies = File("day2.input").readLines().map { it.substringBefore(':').trim() to it.substringAfter(':').trim() }

val partOneAnswer = passwordsAndPolicies.count { (policy, password) ->
    val policyChar = policy.last()
    val policyMin = policy.takeWhile { it.isDigit() }.toInt()
    val policyMax = policy.substringAfter('-').takeWhile { it.isDigit() }.toInt()

    password.count { it == policyChar }.let { it >= policyMin && it <= policyMax }
}

println(partOneAnswer)

val partTwoAnswer = passwordsAndPolicies.count { (policy, password) ->
    val policyChar = policy.last()
    val locationOne = policy.takeWhile { it.isDigit() }.toInt() - 1
    val locationTwo = policy.substringAfter('-').takeWhile { it.isDigit() }.toInt() - 1

    listOf(password[locationOne], password[locationTwo]).count { it == policyChar } == 1
}

println(partTwoAnswer)
