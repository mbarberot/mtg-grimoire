package org.github.mbarberot.mtg.grimoire

import io.javalin.Javalin
import io.javalin.apibuilder.ApiBuilder.get
import io.javalin.apibuilder.ApiBuilder.path
import io.javalin.config.JavalinConfig
import io.javalin.http.staticfiles.Location
import org.github.mbarberot.mtg.grimoire.components.cards.GetCardRoute
import org.github.mbarberot.mtg.grimoire.components.cards.GetCardsRoute
import org.github.mbarberot.mtg.grimoire.components.index.IndexRoute
import org.github.mbarberot.mtg.grimoire.setup.SetupController


class Server(
    val appConfig: AppConfig,
    val indexRoute: IndexRoute,
    val getCardRoute: GetCardRoute,
    val getCardsRoute: GetCardsRoute,
    val setupController: SetupController,
) {
    fun start() {
        val app = Javalin.create { config ->
            configureStaticFiles(config)
            configureServer(config)

            config.routes.apiBuilder {
                path("/") {
                    get(indexRoute::handle)
                    get("setup", setupController::handle)

                    path("api") {
                        path("cards") {
                            get(getCardsRoute::handle)
                            get("{id}", getCardRoute::handle)
                        }
                    }
                }
            }
        }

        app.start()
    }

    private fun configureServer(config: JavalinConfig) {
        config.jetty.host = appConfig.host
        config.jetty.port = appConfig.port
    }

    private fun configureStaticFiles(config: JavalinConfig) {
        // Leverage npm & webjars to easily embed any js lib into the app
        config.staticFiles.enableWebjars()

        // And this is mainly for CSS
        config.staticFiles.add { staticFiles ->
            staticFiles.hostedPath = "/"
            if (appConfig.devMode) {
                staticFiles.directory = "${appConfig.devRoot}/src/main/resources/public"
                staticFiles.location = Location.EXTERNAL
            } else {
                staticFiles.directory = "/public"
                staticFiles.location = Location.CLASSPATH
            }
        }
    }
}