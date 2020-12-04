import java.io.File

val passports = File("day4.input").readText().split("\n\n")

val validPassports = passports.count { passport ->
    val pairs = passport.split('\n').flatMap { it.split(' ') }
    val keys = pairs.map { it.substringBefore(':') }
    keys.sorted().filterNot { it == "cid" } == listOf("byr", /*"cid",*/ "ecl", "eyr", "hcl", "hgt", "iyr", "pid")
}

println(validPassports)

val validPassportsPartTwo = passports.count { passport ->
    val raw = passport.split('\n').flatMap { it.split(' ') }
    val passportData = raw.map { it.substringBefore(':') to it.substringAfter(':') }.toMap()
    listOf(
        with(passportData["byr"] ?: "") { length == 4 && toInt() >= 1920 && toInt() <= 2002 },
        with(passportData["iyr"] ?: "") { length == 4 && toInt() >= 2010 && toInt() <= 2020 },
        with(passportData["eyr"] ?: "") { length == 4 && toInt() >= 2020 && toInt() <= 2030 },
        with(passportData["hgt"] ?: "") {
            when (dropWhile { it.isDigit() }) {
                "cm" -> takeWhile { it.isDigit() }.toInt().let { it >= 150 && it <= 193 }
                "in" -> takeWhile { it.isDigit() }.toInt().let { it >= 59 && it <= 76 }
                else -> false
            }
        },
        with(passportData["hcl"] ?: "") { startsWith('#') && drop(1).matches(Regex("[0-9a-f]*")) },
        passportData["ecl"] ?: "" in listOf("amb", "blu", "brn", "gry", "grn", "hzl", "oth"),
        with(passportData["pid"] ?: "") { all { it.isDigit() } && length == 9 },
    ).all { it }
}

println(validPassportsPartTwo)

