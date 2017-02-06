package org.github.mbarberot.mtg.grimoire.controller

import com.github.salomonbrys.kodein.Kodein
import com.github.salomonbrys.kodein.conf.global
import com.github.salomonbrys.kodein.instance

class Controller {
    fun getCardController() = CardController(Kodein.global.instance())
    fun getIndexController() = IndexController()
}

