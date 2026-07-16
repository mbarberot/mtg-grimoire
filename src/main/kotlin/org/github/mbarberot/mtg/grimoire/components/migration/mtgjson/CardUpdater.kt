package org.github.mbarberot.mtg.grimoire.components.migration.mtgjson

import org.github.mbarberot.mtg.grimoire.AppConfig
import org.github.mbarberot.mtg.grimoire.components.cards.Card
import org.github.mbarberot.mtg.grimoire.components.cards.CardStore

class CardUpdater(
    private val appConfig: AppConfig,
    private val cardStore: CardStore,
    private val tagGenerator: TagGenerator = TagGenerator(),
) {

    fun updateCards(sets: List<MTGSet>) {
        cardStore.removeAll()
        sets.forEach { set -> loadCards(set) }
    }

    private fun loadCards(set: MTGSet) {
        set.cards.forEach { loadCard(set, it) }
    }

    private fun loadCard(set: MTGSet, mtgCard: MTGCard) {
        val languageData = mtgCard.foreignData.find { it.language == appConfig.language }
        if (languageData != null) {
            cardStore.addCard(
                Card(
                    multiverseId = languageData.multiverseId.toString(),
                    name = languageData.name,
                    manaCost = mtgCard.manaCost,
                    set = set.name,
                    text = languageData.text,
                    power = mtgCard.power,
                    toughness = mtgCard.toughness,
                    type = languageData.type ?: "",
                    tags = tagGenerator.generateTags(languageData)
                )
            )
        }
    }
}