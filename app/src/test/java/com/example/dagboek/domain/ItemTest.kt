package com.example.dagboek.domain

import com.example.dagboek.domain.Item
import org.junit.Assert.assertEquals
import org.junit.Test

class ItemTest {

    @Test
    fun `kei mooie naam voor een test`() {

        // Arrange
        val sut = Item(
            1234,
            "12/27/20",
            "some content",
            "body content"
        )
        val expected = "my first title (some content)"

        // Act
        val actual = sut.toString()

        // Assert
        assertEquals(expected, actual)
    }
}