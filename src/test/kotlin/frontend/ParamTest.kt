package frontend

import io.kotest.matchers.ints.shouldBeGreaterThan
import io.kotest.matchers.nulls.shouldNotBeNull
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import org.junit.jupiter.params.provider.NullAndEmptySource
import org.junit.jupiter.params.provider.ValueSource

class ParamTest {
    @ParameterizedTest
    @CsvSource(
        delimiter = '|',
        quoteCharacter = '"',
        textBlock = """
#------------------------- 
# FRUIT      | RANK
#-------------------------
apple        | 1
banana       | 2
"lemon lime" | 0xF1
strawberry   | 10_000
#-------------------------
"""
)
    fun testWithCsvSourceWithComment(fruit: String?, rank: Int) {
        fruit.shouldNotBeNull()
        rank.shouldBeGreaterThan(0)
    }

    @ParameterizedTest
    @ValueSource(strings = ["apple", "banana", "lemon lime", "strawberry"])
    fun testWithValueSource(fruit: String) {
        fruit.shouldNotBeNull()
    }

    @ParameterizedTest
    @NullAndEmptySource
    fun testWithNullAndEmptySource(fruit: String?) {
        if (!fruit.isNullOrEmpty()) {
            fruit.shouldNotBeNull()
        }
    }
}