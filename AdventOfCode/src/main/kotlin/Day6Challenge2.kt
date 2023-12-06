import java.math.BigInteger

fun main(args: Array<String>) {

    val fullFile = TxtFileLoader::class.java.getResource("day6challenge1.txt")?.readText() ?: ""
    val lines = fullFile.split("\n")
    val time = extractNumbersFromLine2(lines.get(0))
    val distance = extractNumbersFromLine2(lines.get(1))
    // Total time (T)
    // Time for charging (t_c)
    // Time for travelling (t_t)
    // Distance travelled (d)
    // Distance to beat (D)
    // t_c^2 - Tt_c^2 + D = 0
    val result = findRoots(1.0, -1.0 * time.toDouble(), distance.toDouble())
    println(result.second - result.first + 1)
}

fun findRoots(a: Double, b: Double, c: Double) : Pair<Int, Int> {
    val descriminant = b*b - 4*a*c
    if (descriminant < 0) {
        return 0 to 0
    }
    val sol1 = (-1*b - Math.pow(descriminant, 0.5)) / 2*a
    val sol2 = (-1*b + Math.pow(descriminant, 0.5)) / 2*a
    return Math.ceil(sol1).toInt() to Math.floor(sol2).toInt()
}

fun extractNumbersFromLine2(line: String) : BigInteger {
    val numbersPart = line.split(":").get(1)
    val filtered = numbersPart.filter { char -> char.isDigit() }
    return filtered.toBigInteger()
}