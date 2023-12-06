
fun main(args: Array<String>) {

    val fullFile = TxtFileLoader::class.java.getResource("day6challenge1.txt")?.readText() ?: ""
    val lines = fullFile.split("\n")
    val times = extractNumbersFromLine(lines.get(0))
    val distances = extractNumbersFromLine(lines.get(1))

    val result = times.zip(distances)
        .map { (time, distance) -> findWinningCombinations(time, distance) }
        .reduce {wins1, wins2 -> wins1 * wins2}
    println(result)
}

fun findWinningCombinations(totalTime: Int, record: Int) : Int {

    val combinations = generateCombinations(totalTime)
    val winningCombinations = combinations
        .withIndex()
        .filter { value -> value.value > record }
    return winningCombinations.size
}

fun generateCombinations(totalTime: Int) : List<Int> {
    return (0..totalTime)
        .map { chargeTime -> getDistance(chargeTime, totalTime - chargeTime) }
}

fun getDistance(chargeTime: Int, remainingTime: Int) : Int {
    val speed = chargeTime
    return remainingTime * speed
}

fun extractNumbersFromLine(line: String) : List<Int> {
    val numbersPart = line.split(":").get(1)
    val intList = mutableListOf<Int>()
    var currentString = ""
    for (i in numbersPart.indices) {
        if (numbersPart.get(i).isDigit()) {
            currentString += numbersPart.get(i)
        } else {
            if (currentString != "") {
                intList.add(currentString.toInt())
                currentString = ""
            }
        }
    }
    if (currentString != "") {
        intList.add(currentString.toInt())
    }
    return intList.toList()
}