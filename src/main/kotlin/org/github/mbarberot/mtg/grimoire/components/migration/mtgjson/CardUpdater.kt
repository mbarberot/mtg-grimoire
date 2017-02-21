package org.github.mbarberot.mtg.grimoire.components.migration.mtgjson

import com.github.salomonbrys.kodein.Kodein
import com.github.salomonbrys.kodein.conf.global
import com.github.salomonbrys.kodein.instance
import org.github.mbarberot.mtg.grimoire.components.cards.Card
import org.github.mbarberot.mtg.grimoire.components.cards.CardStore

class CardUpdater(private val cardStore: CardStore = Kodein.global.instance()) {

    fun updateCards(sets: List<MTGSet>) {
        cardStore.removeAll()
        sets.forEach { set -> loadCards(set) }
    }

    private fun loadCards(set: MTGSet) {
        set.cards.filter({ card -> card.multiverseid != 0 })
                .forEach { loadCard(set, it) }
    }

    private fun loadCard(set: MTGSet, card: MTGCard) {
        cardStore.addCard(Card(
                card.name,
                card.multiverseid.toString(),
                card.manaCost,
                set.name,
                card.text,
                card.power,
                card.toughness,
                card.type,
                emptyList()
        ))
    }
}