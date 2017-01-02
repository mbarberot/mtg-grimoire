package org.github.mbarberot.mtg.grimoire

import com.mongodb.MongoClient
import com.mongodb.MongoClientURI
import org.apache.commons.lang3.StringUtils
import org.apache.commons.logging.Log
import org.apache.commons.logging.LogFactory
import org.jongo.JongoNative
import spark.ModelAndView
import spark.Spark.*
import spark.template.jade.JadeTemplateEngine
import java.util.*

object App {
    val DEFAULT_PORT = 8080
    val LOGGER: Log = LogFactory.getLog(App::class.java.name)

    @JvmStatic fun main(args: Array<String>) {
        configure()
        declareRoutes()
    }

    fun declareRoutes() {
        val templateEngine = initTemplateEngine()
        val dbClient = initDatabaseClient()

        get("/search", { request, response -> ModelAndView(Collections.emptyMap<Any, Any>(), "pages/search") }, templateEngine)
        get("/cards/:id", { request, response -> ModelAndView(Collections.emptyMap<Any, Any>(), "pages/cards/card") }, templateEngine)
        get("/", { request, response -> ModelAndView(Collections.emptyMap<Any, Any>(), "pages/index") }, templateEngine)
    }

    fun initDatabaseClient(): JongoNative {
        val mongoClient = MongoClient(MongoClientURI(System.getenv("DB_URL")))
        val database = mongoClient.getDatabase(System.getenv("DB_NAME"))
        return JongoNative(database)
    }

    fun initTemplateEngine(): JadeTemplateEngine {
        return JadeTemplateEngine()
    }

    fun configure() {
        port(port)
        staticFiles.location("/public")
    }

    val port: Int
        get() {
            val givenPort = System.getenv("PORT")
            if (StringUtils.isNotBlank(givenPort)) {
                try {
                    return Integer.valueOf(givenPort)!!
                } catch (e: NumberFormatException) {
                    LOGGER.warn("Unparseable port in environment : " + givenPort, e)
                }

            }
            return DEFAULT_PORT
        }
}
