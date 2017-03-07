package org.github.mbarberot.mtg.grimoire.migration.mtgjson

import org.github.mbarberot.mtg.grimoire.stores.CardStore
import org.github.mbarberot.mtg.grimoire.models.Card

class CardUpdater(
        private val cardStore: CardStore,
        private val tagGenerator: TagGenerator = TagGenerator()
) {

    fun updateCards(sets: List<MTGSet>) {
        cardStore.removeAll()
        sets.forEach { set -> loadCards(set) }
    }

    private fun loadCards(set: MTGSet) {
        set.cards.filter({ card -> card.multiverseid != 0 })
                .forEach { loadCard(set, it) }
    }

    private fun loadCard(set: MTGSet, mtgCard: MTGCard) {
        cardStore.addCard(Card(
                mtgCard.name,
                mtgCard.multiverseid.toString(),
                mtgCard.manaCost,
                set.name,
                mtgCard.text,
                mtgCard.power,
                mtgCard.toughness,
                mtgCard.type,
                tagGenerator.generateTags(mtgCard)
        ))
    }
}