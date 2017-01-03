package org.github.mbarberot.mtg.grimoire.spark

import com.mongodb.MongoClient
import com.mongodb.MongoClientURI
import org.github.mbarberot.mtg.grimoire.misc.config.Configuration
import org.github.mbarberot.mtg.grimoire.model.beans.Card
import org.jongo.JongoNative
import spark.ModelAndView
import spark.Spark.*
import spark.template.jade.JadeTemplateEngine
import java.util.*

object App {
    
    @JvmStatic fun main(args: Array<String>) {
        val config = Configuration()

        configure(config)
        declareRoutes()
    }

    private fun configure(config: Configuration) {
        port(config.getServerPort())
        staticFiles.location("/public")
    }

    private fun declareRoutes() {
        val templateEngine = JadeTemplateEngine()
        val cards = listOf(
                Card("Ajani Goldmane"),
                Card("Archangel Avacyn"),
                Card("Arlin Kord"),
                Card("Ashlok, NightMare Weaver"),
                Card("Ashling the Pilgrim"),
                Card("Ashling, the Extinguisher")
        )
        
        post("/ic/search", { req, res ->
            val query = req.queryParams("q")
            ModelAndView(
                    mapOf(Pair("cards", cards.filter { card -> card.name.toLowerCase().startsWith(query.toLowerCase()) })),
                    "parts/search-results"
            ) 
        }, templateEngine)
        
        get("/", { request, response -> ModelAndView(Collections.emptyMap<Any, Any>(), "pages/index") }, templateEngine)
        
    }

}

/*
val dbClient = initDatabaseClient(config)
fun initDatabaseClient(config: Configuration): JongoNative {
    val mongoClient = MongoClient(MongoClientURI(config.getDatabaseURL()))
    val database = mongoClient.getDatabase(config.getDatabaseName())
    return JongoNative(database)
}
*/

