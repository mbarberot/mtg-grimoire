package org.github.mbarberot.mtg.grimoire.view

import org.github.mbarberot.mtg.grimoire.AppConfig
import org.github.mbarberot.mtg.grimoire.business.searches.SearchMetadata
import org.github.mbarberot.mtg.grimoire.business.searches.SearchResult
import org.github.mbarberot.mtg.grimoire.components.cards.Card
import org.github.mbarberot.mtg.grimoire.components.cards.CardsView
import org.github.mbarberot.mtg.grimoire.initializeHandlebars
import kotlin.test.Test

class ViewTest {

    // TODO approval testing
    val handlebarsConfig = initializeHandlebars(AppConfig(devMode = false))

    @Test
    fun testSearchView() {
        println(
            CardsView(handlebarsConfig).render(
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
}