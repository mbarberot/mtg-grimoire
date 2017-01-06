package org.github.mbarberot.mtg.grimoire.model.managers

import org.bson.types.ObjectId
import org.github.mbarberot.mtg.grimoire.model.beans.Card
import org.jongo.JongoNative

class CardManager(val jongo: JongoNative) {
    fun getCards(): Collection<Card> {
        return jongo
                .getCollection("cards", Card::class.java)
                .find()
                .toList()
    }

    fun searchCards(query: String): Collection<Card> {
        return if (query.isNotEmpty()) {
            getCards().filter { card -> card.name.toLowerCase().startsWith(query.toLowerCase()) }
        } else {
            emptyList()
        }
    }

    fun getCardById(id: String): Card {
        return jongo
                .getCollection("cards", Card::class.java)
                .find(jongo.query("{ multiverseId: '$id'}"))
                .first()
    }
}