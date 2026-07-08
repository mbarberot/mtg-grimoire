package org.github.mbarberot.mtg.grimoire.view

import org.github.mbarberot.mtg.grimoire.business.searches.SearchMetadata
import org.github.mbarberot.mtg.grimoire.business.searches.SearchResult
import org.github.mbarberot.mtg.grimoire.components.cards.Card
import org.github.mbarberot.mtg.grimoire.components.cards.CardView
import org.github.mbarberot.mtg.grimoire.components.cards.CardsView
import org.github.mbarberot.mtg.grimoire.initializeJadeEngine
import kotlin.test.Test

class ViewTest {

    // TODO approval testing

    val jadeConfig = initializeJadeEngine()

    @Test
    fun testSearchView() {
        println(
            CardsView(jadeConfig).render(
                SearchResult(
                    listOf(
                        Card(
                            multiverseId = "1",
                            name = "Foo",
                            set = "Kaladesh",
                            manaCost = "{1}",
                            text = "Some text",
                            power = "4",
                            toughness = "5",
                            type = "Creature",
                            tags = setOf()
                        )
                    ),
                    SearchMetadata(30, 10, 2, "toto")
                )
            )
        )
    }

    @Test
    fun testCardView() {
        println(
            CardView(jadeConfig).render(
                Card(
                    "Foo",
                    "1",
                    "{1}",
                    "Kaladesh",
                    "Some text",
                    "4",
                    "5",
                    "Creature",
                    setOf()
                )
            )
        )
    }
}