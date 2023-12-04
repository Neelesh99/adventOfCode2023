import kotlin.math.max
fun main(args: Array<String>) {

    val fullFile = TxtFileLoader::class.java.getResource("day2challenge1.txt")?.readText() ?: ""
    val lines = fullFile.split("\n")
    val result = getPower(lines)
    println(result)
}

fun combineToPower(rgbCubes: RGBCubes) : Int {
    return rgbCubes.redCount * rgbCubes.blueCount * rgbCubes.greenCount
}

fun getPower(lines: List<String>) : Int {
    return lines.map { line -> getGameNumberToResults(line) }
        .map { (gameNo, resultsString) -> gameNo to convertResultsToRgbCubes(resultsString) }
        .fold(0) {combiner, pairs -> combiner + combineToPower(pairs.second)}
}
