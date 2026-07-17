package org.github.mbarberot.mtg.grimoire.components.migration.mtgjson

import java.util.UUID
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
            uuid = UUID.randomUUID().toString(),
            manaCost = "{1}{W}{U}",
            power = "4",
            toughness = "5",
            setCode = "LRW",
           foreignData = listOf(
               MTGForeignData(
                   multiverseId = 1,
                   name = "Mammouth volant",
                   text = "Vol",
                   language = "French",
                   type = "Creature",
               )
           )
        )

        assertEquals(
            setOf("vol"),
            generator.generateTags(mtgCard.foreignData[0])
        )
    }
}