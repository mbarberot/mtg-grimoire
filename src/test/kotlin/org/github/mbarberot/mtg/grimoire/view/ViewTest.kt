package org.github.mbarberot.mtg.grimoire.view

import org.github.mbarberot.mtg.grimoire.business.searches.SearchMetadata
import org.github.mbarberot.mtg.grimoire.business.searches.SearchResult
import org.github.mbarberot.mtg.grimoire.model.beans.Card
import org.junit.Test

class ViewTest {
    @Test
    fun testSearchView() {
        val html = View().cardsView(
                SearchResult(
                        listOf(Card("Foo", "1", emptyList())),
                        SearchMetadata(30, 10, 2, "toto")
                )
        )
        
        println(html)
    }
    
}