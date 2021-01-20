import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.InputStream
import java.io.OutputStream

object FileUtils {
    fun readFile(filePath: String): String {
        val `is`: InputStream = FileInputStream(filePath)
        val size = `is`.available()
        val stringBuilder = StringBuilder()
        for (i in 0 until size) {
            stringBuilder.append(`is`.read().toChar())
        }
        `is`.close()
        return stringBuilder.toString()
    }

    fun saveFile(filePath: String, content: String): Boolean {
        val os: OutputStream = FileOutputStream(filePath)
        for (element in content) {
            os.write(element.toInt())
        }
        os.close()
        return true
    }
}