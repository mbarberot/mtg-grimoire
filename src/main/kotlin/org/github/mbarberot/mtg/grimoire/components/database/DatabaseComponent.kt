package org.github.mbarberot.mtg.grimoire.components.database

import com.github.salomonbrys.kodein.Kodein
import com.github.salomonbrys.kodein.bind
import com.github.salomonbrys.kodein.conf.global
import com.github.salomonbrys.kodein.singleton
import org.github.mbarberot.mtg.grimoire.api.Component
import org.github.mbarberot.mtg.grimoire.misc.config.Configuration
import org.github.mbarberot.mtg.grimoire.model.Model
import org.jongo.JongoNative

class DatabaseComponent : Component {
    override fun initialize() {
        val model = Model(Configuration()) // TODO init jongo here
        Kodein.global.addImport(Kodein.Module {
            bind<JongoNative>() with singleton { model.dbClient }
        })
    }
}