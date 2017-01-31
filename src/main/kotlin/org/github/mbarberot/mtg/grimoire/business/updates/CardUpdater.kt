package org.github.mbarberot.mtg.grimoire.business.updates

import org.github.mbarberot.mtg.grimoire.business.updates.mtgjson.MTGReader
import org.github.mbarberot.mtg.grimoire.business.updates.mtgjson.beans.MTGCard
import org.github.mbarberot.mtg.grimoire.model.beans.Card
import org.github.mbarberot.mtg.grimoire.model.managers.CardManager

class CardUpdater(
        private val cardManager: CardManager,
        private val reader: MTGReader
) {

    fun updateCards() {
        cardManager.removeAll()
        reader.read().forEach { set -> loadCards(set.cards) }
    }

    private fun loadCards(cards: List<MTGCard>) {
        cards.filter({ card -> card.multiverseid != 0 })
                .forEach { loadCard(it) }
    }

    private fun loadCard(card: MTGCard) {
        cardManager.addCard(Card(
                card.name,
                card.multiverseid.toString(),
                card.manaCost ?: "",
                emptyList()
        ))
    }

}