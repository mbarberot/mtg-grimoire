package org.github.mbarberot.mtg.grimoire.server

import io.javalin.Javalin
import io.javalin.apibuilder.ApiBuilder.*
import io.javalin.http.Context
import io.javalin.http.Handler
import io.javalin.http.staticfiles.Location
import org.github.mbarberot.mtg.grimoire.AppConfig
import org.github.mbarberot.mtg.grimoire.cards.server.GetCardRoute
import org.github.mbarberot.mtg.grimoire.cards.server.GetCardsRoute
import org.github.mbarberot.mtg.grimoire.components.index.IndexRoute
import org.github.mbarberot.mtg.grimoire.components.setup.SetupController
import org.koin.dsl.module

fun provideServer() =
    module {
        single {
            Server(
                get(),
                get(),
                get(),
                get(),
                get(),
            )
        }
    }

class Server(
    val appConfig: AppConfig,
    val indexRoute: IndexRoute,
    val getCardRoute: GetCardRoute,
    val getCardsRoute: GetCardsRoute,
    val setupController: SetupController,
) {
    fun start() {
        val app = Javalin.create { config ->

            config.jetty.host = appConfig.host
            config.jetty.port = appConfig.port

            config.staticFiles.enableWebjars() // Leverage npm & webjars to easily embed any js lib into the app
            config.staticFiles.add { staticFiles -> // And this is mainly for CSS/Fonts
                staticFiles.hostedPath = "/"
                if (appConfig.devMode) {
                    staticFiles.directory = "${appConfig.devRoot}/src/main/resources/public"
                    staticFiles.location = Location.EXTERNAL
                } else {
                    staticFiles.directory = "/public"
                    staticFiles.location = Location.CLASSPATH
                }
            }

            config.routes.apiBuilder {
                path("/") {
                    before("/") { ctx -> ctx.redirect("/app") }
                    get("setup", setupController::handle)
                    path("app") {
                        get(indexRoute::handle)
                    }
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
}