import io.kotest.matchers.shouldBe
import org.example.general.TestWatcherExtension
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(TestWatcherExtension::class)
class WatcherAnnotationTest {

    @Test
    @DisplayName("Демо TestWatcher через @ExtendWith")
    fun watcherDemo() {
        val actual = 2 + 2
        actual shouldBe 4
    }
}