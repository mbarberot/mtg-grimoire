package org.github.mbarberot.mtg.grimoire.mtgapi.impl.converters

import org.github.mbarberot.mtg.grimoire.cards.domain.Card
import org.github.mbarberot.mtg.grimoire.app.config.CardLanguage
import org.github.mbarberot.mtg.grimoire.mtgapi.impl.MTGCard

class MTGCardConverter(private val language: CardLanguage): Converter<MTGCard, Card> {
    override fun convert(it: MTGCard): Card {
        val languageData = it.foreignData.find { it.language == language.value }
        return Card(
            multiverseId = (languageData?.identifiers ?: it.identifiers).multiverseId,
            name = languageData?.name ?: it.name,
            manaCost = it.manaCost,
            set = it.setCode,
            text = languageData?.text ?: it.text,
            power = it.power,
            toughness = it.toughness,
            type = languageData?.type ?: it.type,
            tags = setOf<String>()
        )
    }
}
