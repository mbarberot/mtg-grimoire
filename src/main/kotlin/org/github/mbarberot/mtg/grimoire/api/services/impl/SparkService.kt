package org.github.mbarberot.mtg.grimoire.api.services.impl

import com.github.salomonbrys.kodein.Kodein
import com.github.salomonbrys.kodein.bind
import com.github.salomonbrys.kodein.instance
import org.github.mbarberot.mtg.grimoire.api.services.Service
import spark.Service.ignite
import spark.Service as Spark

class SparkService : Service {
    private val DEFAULT_PORT = 8080
    private val http = ignite()

    override fun init() {
        http.staticFiles.location("/public")
        http.port(System.getenv("PORT")?.toInt() ?: DEFAULT_PORT)

        http.before({ request, response ->
            response.header("Access-Control-Allow-Origin", "*")
            response.header("Access-Control-Request-Method", "*")
            response.header("Access-Control-Allow-Headers", "*")
        })
    }

    override fun registerModule(): Kodein.Module {
        return Kodein.Module {
            bind<spark.Service>() with instance(http)
        }
    }
}