package org.github.mbarberot.mtg.grimoire.controller

import org.github.mbarberot.mtg.grimoire.model.Model

class Controller(val model: Model) {
    fun getCardController() = CardController(model.getCardManager())
    fun getIndexController() = IndexController()
}

