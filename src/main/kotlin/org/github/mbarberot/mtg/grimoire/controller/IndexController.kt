package org.github.mbarberot.mtg.grimoire.controller

import org.github.mbarberot.mtg.grimoire.view.View

class IndexController {
    fun getIndex(view: View): Any = view.indexView()
}

