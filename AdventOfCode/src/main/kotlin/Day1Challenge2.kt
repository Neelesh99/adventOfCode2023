fun main(args: Array<String>) {

    val fullFile = TxtFileLoader::class.java.getResource("day1challenge1.txt")?.readText() ?: ""
    val lines = fullFile.split("\n")

    val result = lines.map { line -> getFirstInclusiveDigit(line) * 10 + getLastInclusiveDigit(line) }
        .reduce { num1, num2 -> num1 + num2 }

    println(result)

}

fun getLastInclusiveDigit(line: String) : Int {
    val reversed = line.reversed()
    val firstWordDigit = getFirstWordDigit(reversed, getReversedWordDigitList())
    val firstDigit = getLastDigit(line)
    if (firstWordDigit is Pair<Int, String>) {
        val indexOfActualDigit = reversed.indexOf(firstDigit.digitToChar())
        return if(indexOfActualDigit < firstWordDigit.first) firstDigit else getReversedWordDigitList().indexOf(firstWordDigit.second) + 1
    }
    return firstDigit
}

fun getFirstInclusiveDigit(line: String) : Int {
    val firstWordDigit = getFirstWordDigit(line, getWordDigitList())
    val firstDigit = getFirstDigit(line)
    if (firstWordDigit is Pair<Int, String>) {
        val indexOfActualDigit = line.indexOf(firstDigit.digitToChar())
        return if(indexOfActualDigit < firstWordDigit.first) firstDigit else getWordDigitList().indexOf(firstWordDigit.second) + 1
    }
    return firstDigit
}

fun getFirstWordDigit(line: String, wordList: List<String>) : Pair<Int, String>? {
    return line.findAnyOf(wordList)
}

fun getReversedWordDigitList() : List<String> {
    return getWordDigitList().map { word -> word.reversed() }
}

fun getWordDigitList() : List<String> {
    return listOf(
        "one",
        "two",
        "three",
        "four",
        "five",
        "six",
        "seven",
        "eight",
        "nine")
}
