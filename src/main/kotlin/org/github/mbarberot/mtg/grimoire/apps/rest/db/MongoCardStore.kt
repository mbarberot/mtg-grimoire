package org.github.mbarberot.mtg.grimoire.apps.rest.db

import org.github.mbarberot.mtg.grimoire.core.models.Card
import org.github.mbarberot.mtg.grimoire.core.stores.CardStore
import org.jongo.JongoNative

class MongoCardStore(val jongo: JongoNative) : CardStore {
    override fun searchCards(query: String, page: Long, size: Int): Collection<Card> {
        val offset = (page - 1) * size
        return getCollection()
                .find(jongo.query("{name: {\$regex: #, \$options: 'i'}}", ".*$query.*"))
                .skip(offset.toInt())
                .limit(size)
                .toList()
    }

    override fun countCards(query: String): Long {
        return getCollection().count(jongo.query("{name: {\$regex: #, \$options: 'i'}}", ".*$query.*"))
    }

    override fun getCardById(id: String): Card =
            getCollection()
                    .find(jongo.query("{ multiverseId: '$id'}"))
                    .first()

    override fun addCard(card: Card) = getCollection().insertOne(card)

    override fun removeAll() {
        getCollection().deleteMany(jongo.query("{}"))
    }

    private fun getCollection() = jongo.getCollection("cards", Card::class.java)

}

