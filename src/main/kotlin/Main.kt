import FileUtils.getFileFromDirectory
import FileUtils.readFile
import FileUtils.saveFile
import com.google.gson.GsonBuilder
import com.google.gson.JsonElement
import com.google.gson.JsonObject

val gson = GsonBuilder().setPrettyPrinting().create()

val pathInput = "/Users/neil.m/0Developer/KotlinScripts/untitled/src/main/resources/test_input.json"
val pathOutput = "/Users/neil.m/0Developer/KotlinScripts/untitled/src/main/resources/test_output.json"

fun main() {
    // Read file paths from directory
    val originalFiles = getFileFromDirectory("/Users/neil.m/0Developer/KotlinScripts/untitled/src/main/resources/")
    println(originalFiles)

    // Read JSON File
    val fileContent = readFile(pathInput) // or originalFiles[1].getPath()
    val originalJsonObject = gson.fromJson(fileContent, JsonObject::class.java)
    println(originalJsonObject)

    // Check if value contained
    println(originalJsonObject.has("properties")) // true
    println(originalJsonObject.has("lastName")) // false
    println(search(originalJsonObject, "lastName") != null) // true

    // Search for nested node with search key
    println(search(originalJsonObject, "minimum") != null) // true
    print(gson.toJson(search(originalJsonObject, "minimum"))) // returns containing node

    // Modify JSON
    sample_AddProperty(originalJsonObject)
    sample_RemoveProperty(originalJsonObject)

    // Save Modified JSON
    val newfileContent = gson.toJson(originalJsonObject)
    saveFile(pathOutput, newfileContent)
}

/**
 * Returns json object if found
 */
fun search(jsonObject: JsonObject, searchKey: String): JsonObject? {
    for (key in jsonObject.keySet()) {
        if (key == searchKey) return jsonObject
        if (jsonObject.get(key) is JsonObject) {
            val obFound = search(jsonObject.getAsJsonObject(key), searchKey)
            if (obFound != null) return obFound
        }
    }
    return null
}

fun sample_AddProperty(originalJsonObject: JsonObject) {
    val propertyJson = """
    {
      "type": "string",
      "description": "The person's nick name."
    } 
    """
    val jsonElementToAdd = gson.fromJson(propertyJson, JsonElement::class.java)
    val properties = originalJsonObject.getAsJsonObject("properties")
    properties.add("nickName", jsonElementToAdd)
}

fun sample_RemoveProperty(originalJsonObject: JsonObject) {
    originalJsonObject.getAsJsonObject("properties").remove("lastName")
}
