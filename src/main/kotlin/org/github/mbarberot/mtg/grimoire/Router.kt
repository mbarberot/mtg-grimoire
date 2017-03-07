package org.github.mbarberot.mtg.grimoire

import com.github.salomonbrys.kodein.Kodein
import com.github.salomonbrys.kodein.instance
import org.github.mbarberot.mtg.grimoire.resources.CardResource
import org.github.mbarberot.mtg.grimoire.view.JsonView
import spark.Response
import spark.Service as Spark

class Router(val http: Spark, val services: Kodein) {
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
                val cards = cardResource.getCards(
                        request.queryParams("query") ?: "",
                        request.queryParams("page")?.toLong() ?: 1,
                        request.queryParams("size")?.toInt() ?: 10
                )
                mapOf(Pair("cards", cards))
            }
        })
    }

    private fun apiIndexRoute() {
        http.get("/api", { request, response ->
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