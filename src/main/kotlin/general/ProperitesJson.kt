package general

import tools.jackson.module.kotlin.jacksonObjectMapper
import tools.jackson.module.kotlin.readValue

object PropertiesJson {

    private const val DEFAULT_CONFIG_FILE = "/example.json"

    val jsonProps: PropsModel by lazy {
        val fileName = System.getProperty("env_config", DEFAULT_CONFIG_FILE)

        val stream = PropertiesJson::class.java.getResourceAsStream(fileName)
            ?: throw IllegalStateException("Config file not found")

        val mapper = jacksonObjectMapper()

        stream.use {
            mapper.readValue<PropsModel>(it)
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