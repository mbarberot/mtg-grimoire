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
                .mapTo<JdbiCard>()
                .list()
                .map { card -> buildCard(card) }
        }
    }

    private fun buildCard(dbValue: JdbiCard): Card {
        val tags = jdbi.withHandleUnchecked { handle ->
            handle.createQuery(
                """
               SELECT t.name
               FROM cardTags ct
               INNER JOIN tags t on t.name = ct.tagName 
               WHERE ct.cardId = :multiverseId
           """.trimIndent()
            )
                .bind("multiverseId", dbValue.multiverseId)
                .mapTo<String>()
                .set()
        }

        return Card(
            multiverseId = dbValue.multiverseId,
            set = dbValue.setCode,
            name = dbValue.name,
            type = dbValue.type,
            manaCost = dbValue.manaCost,
            text = dbValue.text,
            power = dbValue.power,
            toughness = dbValue.toughness,
            tags = tags
        )
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
                .mapTo<JdbiCard>()
                .list()
                .firstOrNull()
                ?.let { card -> buildCard(card) }
        }
    }

    override fun addCard(card: Card) {
        addSet(card.set) // TODO full set data here plz :)
        createOrUpdateCard(card)
        createOrUpdateTags(card.multiverseId, card.tags)
    }

    private fun addSet(setCode: String) {
        jdbi.useHandleUnchecked { handle ->
            handle.createUpdate(
                """
                        MERGE INTO sets (code)
                        VALUES (:code)
                    """.trimIndent()
            )
                .bind("code", setCode)
                .execute()
        }
    }

    private fun createOrUpdateCard(card: Card) {
        jdbi.useHandleUnchecked { handle ->
            handle.createUpdate(
                """
                        MERGE INTO cards (multiverseId, setCode, name, type, manaCost, text, power, toughness)
                        VALUES (:multiverseId, :setCode, :name, :type, :manaCost, :text, :power, :toughness)
                    """.trimIndent()
            )
                .bind("multiverseId", card.multiverseId)
                .bind("setCode", card.set)
                .bind("name", card.name)
                .bind("type", card.type)
                .bind("manaCost", card.manaCost)
                .bind("text", card.text)
                .bind("power", card.power)
                .bind("toughness", card.toughness)
                .execute()
        }
    }

    private fun createOrUpdateTags(multiverseId: String, tags: Set<String>) {
        jdbi.useHandleUnchecked { handle ->
            tags.forEach { tag ->
                handle.createUpdate("MERGE INTO tags (name) VALUES (:name)")
                    .bind("name", tag)
                    .execute()

                handle.createUpdate(
                    """
                    MERGE INTO cardTags (cardId, tagName) 
                    VALUES (:multiverseId, :tag)
                    """.trimIndent()
                )
                    .bind("multiverseId", multiverseId)
                    .bind("tag", tag)
                    .execute()
            }
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

data class JdbiCard(
    val multiverseId: String,
    val setCode: String,
    val name: String,
    val type: String,
    val manaCost: String?,
    val text: String?,
    val power: String?,
    val toughness: String?,
)
