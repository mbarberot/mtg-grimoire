package org.github.mbarberot.mtg.grimoire.controller

import com.github.salomonbrys.kodein.Kodein
import com.github.salomonbrys.kodein.conf.global
import com.github.salomonbrys.kodein.instance
import org.github.mbarberot.mtg.grimoire.view.jade.IndexView

class IndexController {
    fun getIndex(): Any = IndexView(Kodein.global.instance()).render()
}

