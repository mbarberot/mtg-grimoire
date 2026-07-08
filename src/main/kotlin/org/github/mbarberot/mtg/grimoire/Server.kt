package org.github.mbarberot.mtg.grimoire

import io.javalin.Javalin
import io.javalin.config.JavalinConfig
import io.javalin.http.staticfiles.Location
import org.github.mbarberot.mtg.grimoire.components.cards.GetCardRoute
import org.github.mbarberot.mtg.grimoire.components.cards.GetCardsRoute
import org.github.mbarberot.mtg.grimoire.components.index.IndexRoute

private const val DEFAULT_PORT = 8080

class Server(
    val indexRoute: IndexRoute,
    val getCardRoute: GetCardRoute,
    val getCardsRoute: GetCardsRoute,
) {
    fun start() {
        val app = Javalin.create { config ->
            configureStaticFiles(config)
            configureServer(config)

            config.routes.get("/", indexRoute::handle)
            config.routes.get("/api/cards", getCardsRoute::handle)
            config.routes.get("/api/cards/{id}", getCardRoute::handle)
        }

        app.start()
    }

    private fun configureServer(config: JavalinConfig) {
        config.jetty.host = "0.0.0.0"
        config.jetty.port = System.getenv("PORT")?.toInt() ?: DEFAULT_PORT
    }

    private fun configureStaticFiles(config: JavalinConfig) {
        config.staticFiles.add { staticFiles ->
            staticFiles.hostedPath = "/"
            staticFiles.directory = "/public"
            staticFiles.location = Location.CLASSPATH
        }
    }
}