package org.github.mbarberot.mtg.grimoire.cards.domain

import org.github.mbarberot.mtg.grimoire.cards.storage.api.CardStore
import org.github.mbarberot.mtg.grimoire.tags.domain.TagGenerator
import java.util.logging.Logger

class CardUpdater(
    private val cardStore: CardStore,
    private val tagGenerator: TagGenerator = TagGenerator(),
) {
    companion object {
        val LOG = Logger.getLogger(CardUpdater::class.java.name)
    }

    fun updateCards(cards: List<Card>) {
        cards.forEach { card ->
            val tags = tagGenerator.generateTags(card)
            cardStore.addCard(card.copy(tags = tags))
        }
        LOG.info { "Updated ${cards.size} cards" }
    }

}