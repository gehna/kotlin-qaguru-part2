package org.example.general

import com.codeborne.selenide.Screenshots
import com.codeborne.selenide.Selenide
import io.qameta.allure.Attachment
import org.example.kotlin.general.Config
import org.junit.platform.engine.TestExecutionResult
import org.junit.platform.launcher.TestExecutionListener
import org.junit.platform.launcher.TestIdentifier
import org.junit.platform.launcher.TestPlan

class TestListener : TestExecutionListener {
    override fun testPlanExecutionStarted(testPlan: TestPlan) {
        println("|-----Test plan started. Total tests: ${testPlan.countTestIdentifiers { it.isTest }} ----|")
        println("Initializing Configurations...").also { Config.getProps }
        println(Config.getProps)
    }

    override fun executionSkipped(testIdentifier: TestIdentifier, reason: String) {
        if (testIdentifier.isTest) println("Ignoring test: {${testIdentifier.displayName} reason: $reason}")
    }

    override fun executionStarted(testIdentifier: TestIdentifier) {
        if (testIdentifier.isTest) {
            println("STARTED: ${testIdentifier.displayName}")
        }
    }

    override fun testPlanExecutionFinished(testPlan: TestPlan) {
        Selenide.closeWebDriver()
        println("|-----Test plan finished-----|")
    }

    override fun executionFinished(testIdentifier: TestIdentifier, testExecutionResult: TestExecutionResult) {
        if (testIdentifier.isTest) println("Finished test: ${testIdentifier.displayName} - Result: ${testExecutionResult.status}")
        if (testExecutionResult.status == TestExecutionResult.Status.FAILED && testIdentifier.displayName != "JUnit Jupiter") {
            attchScreenshot()
        }
    }

    @Attachment(value = "{name}", type = "image/png")
    fun attchScreenshot(name: String = "SCREENSHOT"): ByteArray? {
        return Screenshots.takeScreenShotAsFile()?.readBytes()
    }


}