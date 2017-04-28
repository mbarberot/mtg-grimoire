package org.github.mbarberot.mtg.grimoire.apps.rest

import com.github.salomonbrys.kodein.Kodein
import com.github.salomonbrys.kodein.instance
import org.github.mbarberot.mtg.grimoire.core.resources.CardResource
import org.github.mbarberot.mtg.grimoire.apps.rest.json.JsonView
import spark.Service as Spark

class Router(val inject: Kodein) {

    private val DEFAULT_PORT = 8080
    private val http = spark.Service.ignite()

    fun initialize() {
        setup()
        apiCardsRoutes()
        apiIndexRoute()
    }

    private fun setup() {
        with(http) {
            staticFiles.location("/public")
            port(System.getenv("PORT")?.toInt() ?: DEFAULT_PORT)

            before({ request, response ->
                response.header("Access-Control-Allow-Origin", "*")
                response.header("Access-Control-Request-Method", "*")
                response.header("Access-Control-Allow-Headers", "*")
            })
        }
    }

    private fun apiCardsRoutes() {
        val cardResource = CardResource(inject.instance())
        with(http) {
            get("/api/cards/:id", { request, response ->
                jsonView(response).render { cardResource.getCard(request.params("id")) }
            })

            get("/api/cards", { request, response ->
                jsonView(response).render {
                    cardResource.getCards(
                            request.queryParams("query") ?: "",
                            request.queryParams("page")?.toLong() ?: 1,
                            request.queryParams("size")?.toInt() ?: 10
                    )
                }
            })
        }
    }

    private fun apiIndexRoute() {
        with(http) {
            get("/api", { _, response ->
                jsonView(response).render {
                    mapOf(
                            Pair("version", "1.0.0"),
                            Pair("api", "mtg-grimoire")
                    )
                }
            })
        }
    }

    private fun jsonView(response: spark.Response): JsonView = JsonView(response, inject.instance())
}