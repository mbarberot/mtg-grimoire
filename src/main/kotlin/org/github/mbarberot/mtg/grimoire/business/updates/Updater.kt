package org.github.mbarberot.mtg.grimoire.business.updates

import com.github.salomonbrys.kodein.Kodein
import com.github.salomonbrys.kodein.conf.global
import com.github.salomonbrys.kodein.instance
import org.github.mbarberot.mtg.grimoire.business.updates.mtgjson.MTGReader
import java.net.URL

class Updater(var isReady: Boolean = false) {
    fun update() {
        CardUpdater(Kodein.global.instance(), MTGReader(URL("http://mtgjson.com/json/AllSetsArray.json").openStream())).updateCards()
        isReady = true
    }

    fun needUpdate(): Boolean = false
}

