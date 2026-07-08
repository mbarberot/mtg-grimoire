package org.github.mbarberot.mtg.grimoire.components.migration.mtgjson

import org.github.mbarberot.mtg.grimoire.components.cards.Card
import org.github.mbarberot.mtg.grimoire.components.cards.CardStore

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
        cardStore.addCard(
            Card(
                multiverseId = mtgCard.multiverseid.toString(),
                name = mtgCard.name,
                manaCost = mtgCard.manaCost,
                set = set.name,
                text = mtgCard.text,
                power = mtgCard.power,
                toughness = mtgCard.toughness,
                type = mtgCard.type,
                tags = tagGenerator.generateTags(mtgCard)
            )
        )
    }
}