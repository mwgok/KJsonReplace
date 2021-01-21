import java.io.*

object FileUtils {
    fun getFileFromDirectory(dirPath: String): List<File> {
        val listPaths = mutableListOf<File>()
        val folder = File(dirPath)
        val listOfFiles = folder.listFiles()

        for (file in listOfFiles) {
            if (file.isFile) {
                listPaths.add(file)
            }
        }
        return listPaths
    }

    fun readFile(filePath: String): String {
        val `is`: InputStream = FileInputStream(filePath)
        val reader: Reader = BufferedReader(InputStreamReader(`is`, "UTF-8"))
        val stringBuilder = StringBuilder()
        reader.forEachLine {
            stringBuilder.appendLine(it)
        }
        `is`.close()
        reader.close()
        return stringBuilder.toString()
    }

    fun saveFile(filePath: String, content: String): Boolean {
        File(filePath).parentFile.apply {
            if (!exists() || !isDirectory) {
                mkdirs()
            }
        }
        val os: OutputStream = FileOutputStream(filePath)
        val out: Writer = BufferedWriter(OutputStreamWriter(os, "UTF-8"))
        out.use { out ->
            out.write(content)
        }
        os.close()
        return true
    }
}
