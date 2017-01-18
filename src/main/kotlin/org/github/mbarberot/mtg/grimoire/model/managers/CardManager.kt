package org.github.mbarberot.mtg.grimoire.model.managers

import org.github.mbarberot.mtg.grimoire.model.beans.Card
import org.jongo.JongoNative

class CardManager(val jongo: JongoNative) {
    fun searchCards(query: String, page: Int = 1, size: Int = 10): Collection<Card> {
        return if (query.isNotEmpty()) {
            val offset = (page - 1) * size
            getCollection()
                    .find(jongo.query("{name: {\$regex: #, \$options: 'i'}}", ".*$query.*"))
                    .skip(offset)
                    .limit(size)
                    .toList()
        } else {
            emptyList()
        }
    }

    fun countCards(query: String): Long {
        return if (query.isNotEmpty()) {
            getCollection().count(jongo.query("{name: {\$regex: #, \$options: 'i'}}", ".*$query.*"))
        } else {
            0
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