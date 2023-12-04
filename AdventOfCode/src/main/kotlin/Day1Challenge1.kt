fun main(args: Array<String>) {

    val fullFile = TxtFileLoader::class.java.getResource("day1challenge1.txt")?.readText() ?: ""
    val lines = fullFile.split("\n")

    val result = lines.map { line -> getFirstDigit(line) * 10 + getLastDigit(line) }
        .reduce { num1, num2 -> num1 + num2 }

    println(result)


}

fun getLastDigit(str: String) : Int {
    return getFirstDigit(str.reversed())
}

fun getFirstDigit(str: String) : Int {
    return str.first { char -> char.isDigit() }.digitToInt()
}
