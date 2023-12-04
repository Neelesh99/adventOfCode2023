import kotlin.math.max

data class RGBCubes(
    val redCount: Int,
    val blueCount: Int,
    val greenCount: Int
) {

    fun setRed(count: Int) : RGBCubes {
        return RGBCubes(max(redCount, count), blueCount, greenCount)
    }

    fun setBlue(count: Int) : RGBCubes {
        return RGBCubes(redCount, max(blueCount, count), greenCount)
    }

    fun setGreen(count: Int) : RGBCubes {
        return RGBCubes(redCount, blueCount, max(greenCount, count))
    }

    companion object {

        fun base() : RGBCubes {
            return RGBCubes(0, 0, 0)
        }

    }
}

val MAX_RED_CUBES = 12
val MAX_GREEN_CUBES = 13
val MAX_BLUE_CUBES = 14

fun combine(gameNumberToCubes: Pair<Int, RGBCubes>) : Int {
    val (gameNo, rgbCubes) = gameNumberToCubes
    val redCubesValid = rgbCubes.redCount <= MAX_RED_CUBES
    val greenCubesValid = rgbCubes.greenCount <= MAX_GREEN_CUBES
    val blueCubesValid = rgbCubes.blueCount <= MAX_BLUE_CUBES
    return if (redCubesValid && greenCubesValid && blueCubesValid) gameNo else 0
}

fun main(args: Array<String>) {

    val fullFile = TxtFileLoader::class.java.getResource("day2challenge1.txt")?.readText() ?: ""
    val lines = fullFile.split("\n")
    val result = calculate(lines)
    println(result)
}

fun calculate(lines: List<String>) : Int {

    return lines.map { line -> getGameNumberToResults(line) }
        .map { (gameNo, resultsString) -> gameNo to convertResultsToRgbCubes(resultsString) }
        .fold(0) {combiner, pairs -> combiner + combine(pairs)}

}

fun getGameNumberToResults(line: String) : Pair<Int, String> {
    val strippedOfWhiteSpace = line.filter { char -> !char.isWhitespace() }
    val gameNumberStringToGameResultsString =  strippedOfWhiteSpace.split(":")
    val number = gameNumberStringToGameResultsString
        .get(0)
        .filter { char -> char.isDigit() }
        .toInt()
    return number to gameNumberStringToGameResultsString.get(1)
}

fun convertResultsToRgbCubes(resultString: String) : RGBCubes {
    val splitIntoSubGames = resultString.split(";")
    return splitIntoSubGames
        .map { subGame -> subGame.split(",")
            .fold(RGBCubes.base()) { rgbCubes, subGame -> updateRgbCubes(subGame, rgbCubes) } }
        .reduce{cube1, cube2 -> combineRgbCubes(cube1, cube2)}
}

fun combineRgbCubes(cubes1: RGBCubes, cubes2: RGBCubes) : RGBCubes {

    return RGBCubes(
        max(cubes1.redCount, cubes2.redCount),
        max(cubes1.blueCount, cubes2.blueCount),
        max(cubes1.greenCount, cubes2.greenCount))
}

fun updateRgbCubes(subString: String, rgbCubes: RGBCubes) : RGBCubes {

    val number = subString.filter { char -> char.isDigit() }
        .toInt()
    val colour = subString.filter { char -> !char.isDigit() }

    if (colour == "green") {
        return rgbCubes.setGreen(number)
    }
    if (colour == "red") {
        return rgbCubes.setRed(number)
    }
    if (colour == "blue") {
        return rgbCubes.setBlue(number)
    }
    return rgbCubes
}