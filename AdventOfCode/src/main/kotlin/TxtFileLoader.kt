import java.io.File
import kotlin.io.path.Path

class TxtFileLoader
fun loadFileToLinesOfText(path: String) : List<String> {
    val file = File(path)
    return file.readLines();
}