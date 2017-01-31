package org.github.mbarberot.mtg.grimoire.view.jade

import de.neuland.jade4j.JadeConfiguration

class IndexView(jade: JadeConfiguration) : JadeView(jade) {
    fun render() : String {
        return render(emptyMap(), "pages/index")
    }
}