package org.github.mbarberot.mtg.grimoire.components.index

import org.github.mbarberot.mtg.grimoire.api.Component
import spark.Spark.get

class IndexComponent : Component {
    override fun initialize() {
        get("/", IndexRoute())
    }
}

