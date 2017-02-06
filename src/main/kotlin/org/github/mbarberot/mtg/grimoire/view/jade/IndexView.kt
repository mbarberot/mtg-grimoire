package org.github.mbarberot.mtg.grimoire.view.jade

import de.neuland.jade4j.JadeConfiguration
import org.github.mbarberot.mtg.grimoire.components.jade.JadeView

class IndexView(jade: JadeConfiguration) : JadeView(jade) {
    fun render() : String {
        return render(emptyMap(), "pages/index")
    }
}