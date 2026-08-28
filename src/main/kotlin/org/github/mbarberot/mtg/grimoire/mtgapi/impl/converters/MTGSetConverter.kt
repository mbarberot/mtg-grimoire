package org.github.mbarberot.mtg.grimoire.mtgapi.impl.converters

import org.github.mbarberot.mtg.grimoire.cards.domain.Set
import org.github.mbarberot.mtg.grimoire.app.config.CardLanguage
import org.github.mbarberot.mtg.grimoire.mtgapi.impl.MTGSet

class MTGSetConverter(private val language: CardLanguage) : Converter<MTGSet, Set> {
    override fun convert(data: MTGSet): Set {
        val name = data.getLocalizedName(language) ?: data.name
        return Set(
            name = name,
            code = data.code
        )
    }
}