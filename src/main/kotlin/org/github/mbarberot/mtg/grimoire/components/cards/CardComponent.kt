package org.github.mbarberot.mtg.grimoire.components.cards

import com.github.salomonbrys.kodein.Kodein
import com.github.salomonbrys.kodein.bind
import com.github.salomonbrys.kodein.conf.global
import com.github.salomonbrys.kodein.instance
import com.github.salomonbrys.kodein.provider
import org.github.mbarberot.mtg.grimoire.api.Component
import spark.Spark
import spark.Spark.*

class CardComponent : Component {
    override fun initialize() {
        Kodein.global.addImport(Kodein.Module {
            bind<CardStore>() with provider { CardStore(instance()) }
        })
    }

    override fun declareRoutes() {
        get("/api/cards/:id", GetCardRoute(Kodein.global.instance()))
        get("/api/cards", GetCardsRoute(Kodein.global.instance()))
    }
}

