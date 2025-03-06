package com.banquemisr.designsystem.behaviour

class GuardedTimedEvent {

    private var lastEventTime = 0L

    operator fun invoke(debounceTime: Long = 300L, block: () -> Unit) {
        val currentEventTime = System.currentTimeMillis()
        if (currentEventTime - lastEventTime >= debounceTime) {
            block()
            lastEventTime = currentEventTime
        }
    }
}
