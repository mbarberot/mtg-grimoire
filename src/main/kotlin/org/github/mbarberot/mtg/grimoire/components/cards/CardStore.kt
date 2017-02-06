package org.github.mbarberot.mtg.grimoire.components.cards

import org.github.mbarberot.mtg.grimoire.model.beans.Card
import org.jongo.JongoNative

class CardStore(val jongo: JongoNative) {
    fun searchCards(query: String, page: Long = 1, size: Int = 10): Collection<Card> {
        val offset = (page - 1) * size
        return getCollection()
                .find(jongo.query("{name: {\$regex: #, \$options: 'i'}}", ".*$query.*"))
                .skip(offset.toInt())
                .limit(size)
                .toList()
    }

    fun countCards(query: String): Long {
        return getCollection().count(jongo.query("{name: {\$regex: #, \$options: 'i'}}", ".*$query.*"))
    }

    fun getCardById(id: String): Card =
            getCollection()
                    .find(jongo.query("{ multiverseId: '$id'}"))
                    .first()

    fun addCard(card: Card) = getCollection().insertOne(card)

    fun removeAll() = getCollection().deleteMany(jongo.query("{}"))

    private fun getCollection() = jongo.getCollection("cards", Card::class.java)

}