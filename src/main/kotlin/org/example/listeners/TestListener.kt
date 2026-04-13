package org.example.listeners

import org.junit.platform.launcher.TestExecutionListener
import org.junit.platform.launcher.TestIdentifier
import org.junit.platform.launcher.TestPlan

class TestListener : TestExecutionListener {
    override fun testPlanExecutionStarted(testPlan: TestPlan) {
        println("Test plan started. Total tests: ${testPlan.countTestIdentifiers { it.isTest }}")
    }

    override fun executionStarted(testIdentifier: TestIdentifier) {
        if (testIdentifier.isTest) {
            println("STARTED: ${testIdentifier.displayName}")
        }
    }

    override fun executionFinished(
        testIdentifier: TestIdentifier,
        testExecutionResult: org.junit.platform.engine.TestExecutionResult
    ) {
        if (testIdentifier.isTest) {
            println("FINISHED: ${testIdentifier.displayName} -> ${testExecutionResult.status}")
        }
    }
}
