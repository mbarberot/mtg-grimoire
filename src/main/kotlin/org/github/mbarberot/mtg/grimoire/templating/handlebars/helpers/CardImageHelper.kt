package org.github.mbarberot.mtg.grimoire.templating.handlebars.helpers

import com.github.jknack.handlebars.Helper
import com.github.jknack.handlebars.Options
import org.github.mbarberot.mtg.grimoire.components.cards.Card

class CardImageHelper : Helper<Card> {
    override fun apply(card: Card?, options: Options?): String {
        val imageSrc = "https://gatherer.wizards.com/Handlers/Image.ashx?type=card&multiverseid=${card?.multiverseId ?: ""}"
        return """
            <img src="$imageSrc" alt="" />
        """
    }
}