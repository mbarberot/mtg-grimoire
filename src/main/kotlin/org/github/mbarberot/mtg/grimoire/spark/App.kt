package org.github.mbarberot.mtg.grimoire.spark

import com.mongodb.MongoClient
import com.mongodb.MongoClientURI
import org.github.mbarberot.mtg.grimoire.misc.config.Configuration
import org.jongo.JongoNative
import spark.ModelAndView
import spark.Spark.*
import spark.template.jade.JadeTemplateEngine
import java.util.*

object App {
    
    @JvmStatic fun main(args: Array<String>) {
        val config = Configuration()

        configure(config)
        declareRoutes(config)
    }

    private fun configure(config: Configuration) {
        port(config.getServerPort())
        staticFiles.location("/public")
    }

    private fun declareRoutes(config: Configuration) {
        val templateEngine = JadeTemplateEngine()
        //val dbClient = initDatabaseClient(config)
        
        get("/", { request, response -> ModelAndView(Collections.emptyMap<Any, Any>(), "pages/index") }, templateEngine)
    }

    /*fun initDatabaseClient(config: Configuration): JongoNative {
        val mongoClient = MongoClient(MongoClientURI(config.getDatabaseURL()))
        val database = mongoClient.getDatabase(config.getDatabaseName())
        return JongoNative(database)
    }*/
}
