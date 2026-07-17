package org.github.mbarberot.mtg.grimoire.components.migration.mtgjson

import org.github.mbarberot.mtg.grimoire.AppConfig
import org.github.mbarberot.mtg.grimoire.cards.domain.Card
import org.github.mbarberot.mtg.grimoire.cards.storage.api.CardStore
import org.github.mbarberot.mtg.grimoire.mtgapi.api.MTGCard

class CardUpdater(
    private val appConfig: AppConfig,
    private val cardStore: CardStore,
    private val tagGenerator: TagGenerator = TagGenerator(),
) {

    fun updateCards(cards: List<MTGCard>) {
        cardStore.removeAll()
        loadCards(cards)
    }

    private fun loadCards(cards: List<MTGCard>) {
        cards.forEach { loadCard(it) }
    }

    private fun loadCard(mtgCard: MTGCard) {
        val languageData = mtgCard.foreignData.find { it.language == appConfig.language }
        if (languageData != null) {
            cardStore.addCard(
                Card(
                    multiverseId = languageData.multiverseId.toString(),
                    name = languageData.name,
                    manaCost = mtgCard.manaCost,
                    set = mtgCard.setCode,
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