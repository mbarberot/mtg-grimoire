package org.github.mbarberot.mtg.grimoire.core.migration.mtgjson

import org.github.mbarberot.mtg.grimoire.core.models.Card
import org.github.mbarberot.mtg.grimoire.core.stores.CardStore

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
                null,
                mtgCard.multiverseid.toString(),
                mtgCard.name,
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