package org.github.mbarberot.mtg.grimoire.model.managers

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import org.github.mbarberot.mtg.grimoire.model.Model
import org.github.mbarberot.mtg.grimoire.model.beans.Card
import org.github.mbarberot.mtg.grimoire.model.json.MTGSet
import org.jongo.JongoNative
import java.net.URL

class DatabaseManager(val model: Model, val dbClient: JongoNative, var isReady: Boolean = false) {
    fun update() {
        loadCards(model.getCardManager())
        isReady = true
    }

    private fun loadCards(cardManager: CardManager) {
        val mapper = ObjectMapper().registerKotlinModule()
        cardManager.removeAll()
        URL("http://mtgjson.com/json/AllSetsArray.json").openStream().use { stream ->
            mapper.readValue<List<MTGSet>>(stream).forEach { set ->
                set.cards
                        .filter({ card -> card.multiverseid != 0 })
                        .forEach { card ->
                            cardManager.addCard(Card(card.name, card.multiverseid.toString(), emptyList()))
                        }
            }
        }
    }
}