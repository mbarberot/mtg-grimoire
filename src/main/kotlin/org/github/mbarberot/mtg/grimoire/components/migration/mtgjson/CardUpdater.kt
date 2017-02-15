package org.github.mbarberot.mtg.grimoire.components.migration.mtgjson

import com.github.salomonbrys.kodein.Kodein
import com.github.salomonbrys.kodein.conf.global
import com.github.salomonbrys.kodein.instance
import org.github.mbarberot.mtg.grimoire.components.cards.Card
import org.github.mbarberot.mtg.grimoire.components.cards.CardStore
import org.github.mbarberot.mtg.grimoire.components.migration.mtgjson.MTGCard
import org.github.mbarberot.mtg.grimoire.components.migration.mtgjson.MTGSet

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
                card.thoughness,
                card.type,
                emptyList()
        ))
    }

}