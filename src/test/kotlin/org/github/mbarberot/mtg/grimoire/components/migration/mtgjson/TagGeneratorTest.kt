package org.github.mbarberot.mtg.grimoire.components.migration.mtgjson

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
        val mtgCard = MTGCard(
            multiverseid = 1,
            name = "Flying Mammoth",
            manaCost = "{1}{W}{U}",
            text = "Flying",
            power = "4",
            toughness = "5",
            type = "Creature"
        )

        assertEquals(
            setOf("flying"),
            generator.generateTags(mtgCard)
        )
    }
}