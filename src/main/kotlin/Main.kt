import FileUtils.readFile
import FileUtils.saveFile
import com.google.gson.GsonBuilder
import com.google.gson.JsonElement
import com.google.gson.JsonObject

val gson = GsonBuilder().setPrettyPrinting().create()

val pathInput = "/Users/neil.m/0Developer/KotlinScripts/untitled/src/main/resources/test_input.json"
val pathOutput = "/Users/neil.m/0Developer/KotlinScripts/untitled/src/main/resources/test_output.json"

fun main() {
    // Read JSON File
    val fileContent = readFile(pathInput)
    val originalJsonObject = gson.fromJson(fileContent, JsonObject::class.java)
    println(originalJsonObject)

    // Check if value contained
    println(originalJsonObject.has("lastName"))


    // Modify JSON
    sample_AddProperty(originalJsonObject)
    sample_RemoveProperty(originalJsonObject)

    // Save Modified JSON
    val newfileContent = gson.toJson(originalJsonObject)
    saveFile(pathOutput, newfileContent)
}

fun sample_AddProperty(originalJsonObject: JsonObject){
    val propertyJson = """
    {
      "type": "string",
      "description": "The person's nick name."
    } 
    """.trimIndent()
    val jsonElementToAdd = gson.fromJson(propertyJson,JsonElement::class.java)
    val properties = originalJsonObject.getAsJsonObject("properties")
    properties.add("nickName",jsonElementToAdd)

}

fun sample_RemoveProperty(originalJsonObject: JsonObject){
    originalJsonObject.getAsJsonObject("properties").remove("lastName")
}