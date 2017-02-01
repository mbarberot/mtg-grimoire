package org.github.mbarberot.mtg.grimoire.business.updates

import org.github.mbarberot.mtg.grimoire.business.updates.mtgjson.MTGReader
import org.github.mbarberot.mtg.grimoire.business.updates.mtgjson.beans.MTGCard
import org.github.mbarberot.mtg.grimoire.business.updates.mtgjson.beans.MTGSet
import org.github.mbarberot.mtg.grimoire.model.beans.Card
import org.github.mbarberot.mtg.grimoire.model.managers.CardManager

class CardUpdater(
        private val cardManager: CardManager,
        private val reader: MTGReader
) {

    fun updateCards() {
        cardManager.removeAll()
        reader.read().forEach { set -> loadCards(set, set.cards) }
    }

    private fun loadCards(set: MTGSet, cards: List<MTGCard>) {
        cards.filter({ card -> card.multiverseid != 0 })
                .forEach { loadCard(set, it) }
    }

    private fun loadCard(set: MTGSet, card: MTGCard) {
        cardManager.addCard(Card(
                card.name,
                card.multiverseid.toString(),
                card.manaCost,
                set.name,
                card.text,
                card.power,
                card.thoughness,
                card.type,
                emptyList()
        ))
    }

}