import com.google.gson.JsonObject

object ParseUtils {
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
}
