package org.github.mbarberot.mtg.grimoire.migration.mtgjson

import com.nhaarman.mockito_kotlin.doReturn
import com.nhaarman.mockito_kotlin.mock
import org.github.mbarberot.mtg.grimoire.core.migration.mtgjson.MTGCard
import org.github.mbarberot.mtg.grimoire.core.migration.mtgjson.TagGenerator
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class TagGeneratorTest {
    lateinit var generator: TagGenerator

    @Before
    fun setUp() {
        generator = TagGenerator()
    }

    @Test
    fun abilityTags() {
        val mtgCard = mock<MTGCard> {
            on { text } doReturn "Flying"
        }
        
        assertEquals(
                listOf("flying"),
                generator.generateTags(mtgCard)
        )
    }
}