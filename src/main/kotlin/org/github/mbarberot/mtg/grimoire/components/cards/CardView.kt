package org.github.mbarberot.mtg.grimoire.components.cards

import de.neuland.jade4j.JadeConfiguration
import org.github.mbarberot.mtg.grimoire.components.cards.Card
import org.github.mbarberot.mtg.grimoire.components.jade.JadeView

class CardView(jade: JadeConfiguration) : JadeView(jade) {
    fun render(card: Card): String {
        return render(mapOf(Pair("card", card)), "parts/card")
    }
}