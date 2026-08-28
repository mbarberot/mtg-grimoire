package org.github.mbarberot.mtg.grimoire.mtgapi.api

import org.github.mbarberot.mtg.grimoire.cards.domain.Card
import org.github.mbarberot.mtg.grimoire.cards.domain.Set

interface MTGApi {
    fun getVersion(): String
    fun getSets(): List<Set>
    fun getCards(setCode: String): List<Card>
}
