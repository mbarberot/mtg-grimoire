package org.github.mbarberot.mtg.grimoire.api

import com.github.salomonbrys.kodein.Kodein
import com.github.salomonbrys.kodein.instance
import org.github.mbarberot.mtg.grimoire.api.resources.CardResource
import org.github.mbarberot.mtg.grimoire.api.view.JsonView
import spark.Response
import spark.Service as Spark

class Router(val http: spark.Service, val services: Kodein) {
    fun initialize() {
        apiCardsRoutes()
        apiIndexRoute()
    }

    private fun apiCardsRoutes() {
        val cardResource = CardResource(services.instance())

        http.get("/api/cards/:id", { request, response ->
            jsonView(response).render { cardResource.getCard(request.params("id")) }
        })

        http.get("/api/cards", { request, response ->
            jsonView(response).render {
                cardResource.getCards(
                        request.queryParams("query") ?: "",
                        request.queryParams("page")?.toLong() ?: 1,
                        request.queryParams("size")?.toInt() ?: 10
                )
            }
        })
    }

    private fun apiIndexRoute() {
        http.get("/api", { _, response ->
            jsonView(response).render {
                mapOf(
                        Pair("version", "1.0.0"),
                        Pair("api", "mtg-grimoire")
                )
            }
        })
    }

    private fun jsonView(response: Response): JsonView = JsonView(response, services.instance())
}