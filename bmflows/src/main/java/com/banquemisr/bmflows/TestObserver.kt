package com.banquemisr.bmflows

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import org.junit.Assert.*

class TestObserver<T>(private val flow: Flow<T>, coroutineScope: CoroutineScope) {
    private val emittedValues = mutableListOf<T>()
    private var flowError: Throwable? = null
    private val job: Job = flow.onEach {
        emittedValues.add(it)
    }.catch {
        flowError = it
    }.launchIn(coroutineScope)

    fun assertError() {
        assertNotNull(flowError)
    }

    fun assertError(throwable: Throwable) {
        assertEquals(flowError, throwable)
    }

    fun assertNoValue() {
        assertTrue(emittedValues.isEmpty())
    }

    fun assertValue(value: T) {
        assertTrue(emittedValues.isNotEmpty())
        assertEquals(value, emittedValues.last())
    }

    fun assertValueHistory(vararg values: T) {
        assertSize(values.size)
        assertSequence(values.toList())
    }

    fun assertSize(size: Int) {
        assertEquals(size, emittedValues.size)
    }

    fun getValues() = emittedValues

    fun stopObserving() {
        job.cancel()
    }

    fun getFlow() = flow

    fun value(): T = getValues().last()

    private fun assertSequence(values: List<T>) {
        for ((index, v) in values.withIndex()) {
            assertEquals(v, emittedValues[index])
        }
    }
}

fun <T> Flow<T>.test(coroutineScope: CoroutineScope = CoroutineScope(UnconfinedTestDispatcher())) =
    TestObserver(this, coroutineScope)
