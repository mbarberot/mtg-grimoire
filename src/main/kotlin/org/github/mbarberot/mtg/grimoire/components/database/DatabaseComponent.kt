package org.github.mbarberot.mtg.grimoire.components.database

import com.fasterxml.jackson.module.kotlin.KotlinModule
import com.github.salomonbrys.kodein.Kodein
import com.github.salomonbrys.kodein.bind
import com.github.salomonbrys.kodein.conf.global
import com.github.salomonbrys.kodein.instance
import com.mongodb.MongoClient
import com.mongodb.MongoClientURI
import org.github.mbarberot.mtg.grimoire.api.Component
import org.jongo.JongoNative
import org.jongo.marshall.jackson.JacksonMapper

class DatabaseComponent : Component {
    override fun initialize() {
        Kodein.global.addImport(Kodein.Module {
            bind<JongoNative>() with instance(initializeJongo())
        })
    }

    fun initializeJongo(): JongoNative {
        val mongoClient = MongoClient(MongoClientURI(System.getenv("DB_URL")))
        val database = mongoClient.getDatabase(System.getenv("DB_NAME"))
        val mapper = JacksonMapper.Builder().registerModule(KotlinModule()).build()

        return JongoNative(database, mapper)
    }
}