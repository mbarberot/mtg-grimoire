package org.github.mbarberot.mtg.grimoire.view.jade

import de.neuland.jade4j.JadeConfiguration
import org.github.mbarberot.mtg.grimoire.model.beans.Card

class CardView(jade: JadeConfiguration) : JadeView(jade) {
    fun render(card: Card): Any {
        return render(mapOf(Pair("card", card)), "parts/card")
    }
}