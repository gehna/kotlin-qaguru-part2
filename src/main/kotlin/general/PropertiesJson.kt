package general

import com.google.gson.Gson
import java.io.InputStreamReader

object PropertiesJson {

    private const val DEFAULT_CONFIG_FILE = "/example.json"

    val jsonProps: PropsModel by lazy {
        val fileName = System.getProperty("env_config", DEFAULT_CONFIG_FILE)

        val stream = PropertiesJson::class.java.getResourceAsStream(fileName)
            ?: throw IllegalStateException("Config file not found: $fileName")

        stream.use { input ->
            InputStreamReader(input).use { reader ->
                Gson().fromJson(reader, PropsModel::class.java)
            }
        }
    }
}

data class PropsModel(
    val browserName: String,
    val browserVersion: String,
    val frontendUrl: String,
    val backendUrl: String,
    val selenoidUrl: String,
)