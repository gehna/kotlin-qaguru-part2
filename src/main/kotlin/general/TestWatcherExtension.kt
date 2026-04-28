package org.example.general

import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.api.extension.TestWatcher
import java.util.Optional

class TestWatcherExtension : TestWatcher {

    override fun testSuccessful(context: ExtensionContext) {
        println("WATCHER SUCCESS: ${context.displayName}")
    }

    override fun testFailed(context: ExtensionContext, cause: Throwable) {
        println("WATCHER FAILED: ${context.displayName} -> ${cause.message}")
    }

    override fun testDisabled(context: ExtensionContext, reason: Optional<String>) {
        println("WATCHER DISABLED: ${context.displayName}, reason=${reason.orElse("no reason")}")
    }
}