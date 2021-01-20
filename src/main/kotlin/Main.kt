import FileUtils.readFile
import FileUtils.saveFile
import java.io.FileInputStream
import java.io.InputStream

fun main() {
    print("Hello World!")

    val path = "/Users/neil.m/0Developer/KotlinScripts/untitled/src/main/kotlin/Main.kt"
    val fileContent = readFile(path)
    val newfileContent = "$fileContent\nok"
    saveFile(path, newfileContent)
}
