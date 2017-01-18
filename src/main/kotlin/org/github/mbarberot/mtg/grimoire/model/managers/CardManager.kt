package org.github.mbarberot.mtg.grimoire.model.managers

import org.github.mbarberot.mtg.grimoire.model.beans.Card
import org.jongo.JongoNative

class CardManager(val jongo: JongoNative) {
    fun searchCards(query: String): Collection<Card> {
        return if (query.isNotEmpty()) {
            getCollection()
                    .find(jongo.query("{name: {\$regex: #, \$options: 'i'}}", ".*$query.*"))
                    .toList()
        } else {
            emptyList()
        }
    }

    fun getCardById(id: String): Card =
            getCollection()
                    .find(jongo.query("{ multiverseId: '$id'}"))
                    .first()

    fun addCard(card: Card) = getCollection().insertOne(card)

    fun removeAll() = getCollection().deleteMany(jongo.query("{}"))

    private fun getCollection() = jongo.getCollection("cards", Card::class.java)
}