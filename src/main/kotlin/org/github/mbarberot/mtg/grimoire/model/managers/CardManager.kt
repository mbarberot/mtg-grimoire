package org.github.mbarberot.mtg.grimoire.model.managers

import org.github.mbarberot.mtg.grimoire.model.beans.Card
import org.jongo.JongoNative

class CardManager(val dbClient: JongoNative) {
    fun getCards(): Collection<Card> {
        return dbClient
                .getCollection("cards")
                .find(Card::class.java)
                .toList()
    }

    fun searchCards(query: String): Collection<Card> {
        return if (query.isNotEmpty()) {
            getCards().filter { card -> card.name.toLowerCase().startsWith(query.toLowerCase()) }
        } else {
            emptyList()
        }
    }
}