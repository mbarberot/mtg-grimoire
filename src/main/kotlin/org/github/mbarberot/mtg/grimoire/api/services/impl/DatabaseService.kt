package org.github.mbarberot.mtg.grimoire.api.services.impl

import com.fasterxml.jackson.module.kotlin.KotlinModule
import com.github.salomonbrys.kodein.Kodein
import com.github.salomonbrys.kodein.bind
import com.github.salomonbrys.kodein.instance
import com.mongodb.MongoClient
import com.mongodb.MongoClientURI
import org.github.mbarberot.mtg.grimoire.api.services.Service
import org.jongo.JongoNative
import org.jongo.marshall.jackson.JacksonMapper

class DatabaseService : Service {
    override fun init() {
    }

    override fun registerModule(): Kodein.Module {
        val mongoClient = MongoClient(MongoClientURI(System.getenv("DB_URL")))
        val database = mongoClient.getDatabase(System.getenv("DB_NAME"))
        val mapper = JacksonMapper.Builder().registerModule(KotlinModule()).build()
        val jongo = JongoNative(database, mapper)

        return Kodein.Module {
            bind<JongoNative>() with instance(jongo)
        }
    }
}