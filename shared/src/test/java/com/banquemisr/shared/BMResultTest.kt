package com.banquemisr.shared

import junit.framework.TestCase.*
import org.junit.Assert.assertThrows
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.kotlin.*

class BMResultTest {
    @Test
    fun `EXPECT GSResult Success can be instantiated correctly`() {
        val sut: BMResult<Int, String> = BMResult.Success(42)

        assertTrue(sut.isSuccess())
        assertFalse(sut.isError())
        assertEquals(42, sut.asSuccess().data)
    }

    @Test
    fun `EXPECT GSResult Error can be instantiated correctly`() {
        val sut: BMResult<Int, String> = BMResult.Error("An error occurred")

        assertTrue(sut.isError())
        assertFalse(sut.isSuccess())
        assertEquals("An error occurred", sut.asError().reason)
    }

    @Test
    fun `EXPECT fold invokes correct branch WHEN using success case`() {
        val value = 42
        val sut: BMResult<Int, String> = BMResult.Success(value)
        val successMock = mock<(Int) -> Int>()
        whenever(successMock.invoke(value)).thenReturn(value * 2)

        val result = sut.fold(
            success = successMock,
            error = { -1 }
        )

        verify(successMock).invoke(value)
        assertEquals(value * 2, result)
    }

    @Test
    fun `EXPECT fold invokes correct branch WHEN using error case`() {
        val sut: BMResult<Int, String> = BMResult.Error(ERROR_MESSAGE)
        val successMock = mock<(Int) -> Int>()

        val result = sut.fold(
            success = successMock,
            error = { -1 }
        )

        verifyNoInteractions(successMock)
        assertEquals(-1, result)
    }

    @Test
    fun `EXPECT map transforms success value`() {
        val sut: BMResult<Int, String> = BMResult.Success(42)

        val result = sut.map { it.toString() }

        assertTrue(result.isSuccess())
        assertEquals("42", result.asSuccess().data)
    }

    @Test
    fun `EXPECT map does not transform error`() {
        val sut: BMResult<Int, String> = BMResult.Error(ERROR_MESSAGE)

        val result = sut.map { it.toString() }

        assertTrue(result.isError())
        assertEquals(ERROR_MESSAGE, result.asError().reason)
    }

    @Test
    fun `EXPECT asSuccess throws ClassCastException WHEN result is Error`() {
        val sut: BMResult<Int, String> = BMResult.Error(ERROR_MESSAGE)

        assertThrows(ClassCastException::class.java) {
            sut.asSuccess()
        }
    }

    @Test
    fun `EXPECT asError throws ClassCastException WHEN result is Success`() {
        val sut: BMResult<Int, String> = BMResult.Success(42)

        assertThrows(ClassCastException::class.java) {
            sut.asError()
        }
    }

    @Test
    fun `EXPECT isSuccess is false WHEN result is Error`() {
        val sut: BMResult<Int, String> = BMResult.Error(ERROR_MESSAGE)

        assertEquals(false, sut.isSuccess())
        assertEquals(true, sut.isError())
    }

    @Test
    fun `EXPECT isError is false WHEN result is Success`() {
        val sut: BMResult<Int, String> = BMResult.Success(42)

        assertEquals(true, sut.isSuccess())
        assertEquals(false, sut.isError())
    }

    private companion object {
        const val ERROR_MESSAGE = "An error occurred"
    }
}
