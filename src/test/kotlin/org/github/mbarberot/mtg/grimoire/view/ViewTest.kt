package org.github.mbarberot.mtg.grimoire.view

import org.github.mbarberot.mtg.grimoire.business.searches.SearchMetadata
import org.github.mbarberot.mtg.grimoire.business.searches.SearchResult
import org.github.mbarberot.mtg.grimoire.components.cards.Card
import org.junit.Test

class ViewTest {
    @Test
    fun testSearchView() {
        println(View().cardsView(
                SearchResult(
                        listOf(Card("Foo", "1", "{1}", "Kaladesh", "Some text", "4", "5", "Creature", emptyList())),
                        SearchMetadata(30, 10, 2, "toto")
                )
        ))
    }

    @Test
    fun testCardView() {
        println(View().cardView(Card("Foo", "1", "{1}", "Kaladesh", "Some text", "4", "5", "Creature", emptyList())))
    }
}