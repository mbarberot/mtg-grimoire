package org.github.mbarberot.mtg.grimoire.database.stores

import org.github.mbarberot.mtg.grimoire.cards.domain.Card
import org.github.mbarberot.mtg.grimoire.cards.storage.api.CardStore
import org.jdbi.v3.core.Jdbi
import org.jdbi.v3.core.kotlin.mapTo
import org.jdbi.v3.core.kotlin.useHandleUnchecked
import org.jdbi.v3.core.kotlin.withHandleUnchecked

class JdbiCardStore(private val jdbi: Jdbi) : CardStore {

    override fun searchCards(
        query: String,
        page: Int,
        size: Int,
    ): Collection<Card> {
        return jdbi.withHandleUnchecked { handle ->
            handle.createQuery(
                """
                SELECT *
                FROM cards
                WHERE LOWER(name) LIKE :query 
                LIMIT :limit
                OFFSET :offset
            """.trimIndent()
            )
                .bind("query", "%${query.lowercase()}%")
                .bind("offset", (page - 1) * size)
                .bind("limit", size)
                .mapTo<Card>()
                .list()
        }
    }

    override fun countCards(query: String): Int {
        return jdbi.withHandleUnchecked { handle ->
            handle.createQuery(
                """
                SELECT COUNT(*)
                FROM cards
                WHERE name LIKE :query 
            """.trimIndent()
            )
                .bind("query", "%${query}%")
                .mapTo<Int>()
                .one()
        }
    }

    override fun getCardById(id: String): Card? {
        return jdbi.withHandleUnchecked { handle ->
            handle.createQuery(
                """
                SELECT *
                FROM cards
                WHERE multiverseId = :id
            """.trimIndent()
            )
                .bind("id", id)
                .mapTo<Card>()
                .one()
        }
    }

    override fun addCard(card: Card) {
        jdbi.useHandleUnchecked { handle ->
            handle.createUpdate(
                """
                INSERT INTO cards (multiverseId, `set`, name, type, manaCost, text, power, toughness, tags)
                VALUES (:multiverseId, :set, :name, :type, :manaCost, :text, :power, :toughness, :tags)
            """.trimIndent()
            )
                .bind("multiverseId", card.multiverseId)
                .bind("set", card.set)
                .bind("name", card.name)
                .bind("type", card.type)
                .bind("manaCost", card.manaCost)
                .bind("text", card.text)
                .bind("power", card.power)
                .bind("toughness", card.toughness)
                .bind("tags", card.tags.joinToString(","))
                .execute()
        }
    }

    override fun removeAll() {
        jdbi.useHandleUnchecked { handle ->
            handle.createUpdate("DELETE FROM cards")
                .execute()
        }
    }

    override fun countAll(): Int {
        return jdbi.withHandleUnchecked { handle ->
            handle.createQuery("SELECT COUNT(*) FROM cards")
                .mapTo<Int>()
                .one()
        }
    }

}
