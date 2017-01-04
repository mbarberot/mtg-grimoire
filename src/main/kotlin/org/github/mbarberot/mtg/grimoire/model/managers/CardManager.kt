package org.github.mbarberot.mtg.grimoire.model.managers

import org.github.mbarberot.mtg.grimoire.model.beans.Card
import org.jongo.JongoNative

class CardManager(val dbClient: JongoNative) {
    fun getCards(): Collection<Card> {
       return listOf(
                Card("Ajani Goldmane", "222369"),
                Card("Archangel Avacyn", "411061"),
                Card("Arlin Kord", "411327"),
                Card("Ashlok, NightMare Weaver", "374496"),
                Card("Ashling the Pilgrim", "155366"),
                Card("Ashling, the Extinguisher", "181741")
        )
    }

    fun searchCards(query: String): Collection<Card> {
        return if (query.isNotEmpty()) {
            getCards().filter { card -> card.name.toLowerCase().startsWith(query.toLowerCase()) }
        } else {
            emptyList()
        }
    }
}