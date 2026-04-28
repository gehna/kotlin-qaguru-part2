import general.PropertiesJson.jsonProps
import org.example.kotlin.general.Config
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class PropTest {

    @Test
    @DisplayName(value = "Проверка загрузки свойств из файла")
    fun testPropertiesLoading() {
//        println("Property file: $properties")
        println("New property file: ${Config.getProps.moonHost}")
        println("Gson property file: ${jsonProps.browserName}")
    }


}