package org.github.mbarberot.mtg.grimoire.business.updates

import org.github.mbarberot.mtg.grimoire.business.updates.mtgjson.MTGReader
import org.github.mbarberot.mtg.grimoire.model.Model
import java.net.URL

class Updater(
        val model: Model, 
        var isReady: Boolean = false
) {
    fun update() {
        CardUpdater(model.getCardManager(), MTGReader(URL("http://mtgjson.com/json/AllSetsArray.json").openStream())).updateCards()
        isReady = true
    }

    fun needUpdate(): Boolean = false
}

