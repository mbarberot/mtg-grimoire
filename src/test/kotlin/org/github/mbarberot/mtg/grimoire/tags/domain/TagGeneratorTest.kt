package org.github.mbarberot.mtg.grimoire.tags.domain

import org.github.mbarberot.mtg.grimoire.cards.domain.Card
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

class TagGeneratorTest {
    lateinit var generator: TagGenerator

    @BeforeTest
    fun setUp() {
        generator = TagGenerator()
    }

    @Test
    fun abilityTags() {
        val card = Card(
            manaCost = "{1}{W}{U}",
            power = "4",
            toughness = "5",
            set = "LRW",
            multiverseId = "1",
            name = "Mammouth volant",
            text = "Vol",
            type = "Creature",
        )

        assertEquals(
            setOf("vol"),
            generator.generateTags(card)
        )
    }
}