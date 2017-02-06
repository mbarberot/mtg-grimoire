package org.github.mbarberot.mtg.grimoire.view

import de.neuland.jade4j.JadeConfiguration
import org.github.mbarberot.mtg.grimoire.business.searches.SearchMetadata
import org.github.mbarberot.mtg.grimoire.business.searches.SearchResult
import org.github.mbarberot.mtg.grimoire.components.cards.Card
import org.github.mbarberot.mtg.grimoire.components.jade.JadeComponent
import org.github.mbarberot.mtg.grimoire.view.jade.CardView
import org.github.mbarberot.mtg.grimoire.view.jade.CardsView
import org.junit.Test

class ViewTest {

    val jadeConfig = JadeComponent().initializeJadeEngine()

    @Test
    fun testSearchView() {
        println(CardsView(jadeConfig).render(
                SearchResult(
                        listOf(Card("Foo", "1", "{1}", "Kaladesh", "Some text", "4", "5", "Creature", emptyList())),
                        SearchMetadata(30, 10, 2, "toto")
                )
        ))
    }

    @Test
    fun testCardView() {
        println(CardView(jadeConfig).render(
                Card("Foo", "1", "{1}", "Kaladesh", "Some text", "4", "5", "Creature", emptyList()))
        )
    }
}