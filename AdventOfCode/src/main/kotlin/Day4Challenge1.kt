
fun main(args: Array<String>) {

    val fullFile = TxtFileLoader::class.java.getResource("day4challenge1.txt")?.readText() ?: ""
    val lines = fullFile.split("\n")
    val result = lines.map { line -> splitIntoWinnersAndNumbers(line) }
                .map { entry -> countWinners(entry) }
                .map { count -> cardValue(count) }
                .reduce { val1, val2 -> val1 + val2 }
    println(result)
}

fun splitIntoWinnersAndNumbers(line: String) : Pair<List<Int>, List<Int>> {

    val parts = line.split(": ")
    val sections = parts.get(1).split(" | ")
    val winners = sections.get(0).split(" ").filter { s -> s.all { char -> char.isDigit() } && s.isNotEmpty() }
    val cards = sections.get(1).split(" ").filter { s -> s.all { char -> char.isDigit() } && s.isNotEmpty() }
    return Pair(winners.map { it.toInt() }, cards.map { it.toInt() })
}

fun countWinners(entry: Pair<List<Int>, List<Int>>) : Int {

    val (winners, cards) = entry

    val cardSet = cards.toSet()

    return winners.fold(0){ comb, winner -> if(cardSet.contains(winner)) comb + 1 else comb }

}

fun cardValue(power: Int) : Int {
    return Math.pow(2.0, power.toDouble()-1).toInt()
}