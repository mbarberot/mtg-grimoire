package org.github.mbarberot.mtg.grimoire.app

import com.github.salomonbrys.kodein.Kodein
import com.github.salomonbrys.kodein.bind
import com.github.salomonbrys.kodein.conf.global
import com.github.salomonbrys.kodein.provider
import javafx.application.Application
import org.github.mbarberot.mtg.grimoire.app.db.H2CardStore
import org.github.mbarberot.mtg.grimoire.app.db.H2VersionStore
import org.github.mbarberot.mtg.grimoire.core.Grimoire
import org.github.mbarberot.mtg.grimoire.core.stores.CardStore
import org.github.mbarberot.mtg.grimoire.core.stores.VersionStore

fun main(args: Array<String>) {

    val grimoire = Grimoire()

    with(Kodein.global) {
        addImport(Kodein.Module {
            bind<CardStore>() with provider { H2CardStore() }
            bind<VersionStore>() with provider { H2VersionStore() }
        })
        addImport(grimoire.coreModule)
    }

    grimoire.launch()

    Application.launch(GrimoireApp::class.java, *args)
}