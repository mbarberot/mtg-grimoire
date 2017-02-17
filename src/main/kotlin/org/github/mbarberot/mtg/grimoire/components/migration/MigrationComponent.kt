package org.github.mbarberot.mtg.grimoire.components.migration

import com.github.salomonbrys.kodein.Kodein
import com.github.salomonbrys.kodein.bind
import com.github.salomonbrys.kodein.conf.global
import com.github.salomonbrys.kodein.instance
import com.github.salomonbrys.kodein.provider
import org.github.mbarberot.mtg.grimoire.api.Component
import org.github.mbarberot.mtg.grimoire.components.migration.mtgjson.MTGApi

class MigrationComponent : Component {
    override fun initialize() {
        Kodein.global.addImport(Kodein.Module {
            bind<VersionStore>() with provider { VersionStore(instance()) }
            bind<MTGApi>() with provider { MTGApi() }
        })
    }

    override fun declareRoutes() {
    }
}