package org.github.mbarberot.mtg.grimoire.model.managers

import org.github.mbarberot.mtg.grimoire.model.beans.Card

class CardManager {
    fun getCards(): Collection<Card> {
        return listOf(
                Card("Ajani Goldmane"),
                Card("Archangel Avacyn"),
                Card("Arlin Kord"),
                Card("Ashlok, NightMare Weaver"),
                Card("Ashling the Pilgrim"),
                Card("Ashling, the Extinguisher")
        )
    }

}