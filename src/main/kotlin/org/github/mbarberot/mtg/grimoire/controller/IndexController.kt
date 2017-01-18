package org.github.mbarberot.mtg.grimoire.controller

import org.github.mbarberot.mtg.grimoire.view.View

class IndexController : AbstractController() {
    fun getIndex(view: View): Any = render(view, "pages/index", emptyMap())
}

