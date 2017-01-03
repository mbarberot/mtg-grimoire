package org.github.mbarberot.mtg.grimoire.misc.config


class Configuration(val env: Environment = Environment()) {
    val DEFAULT_PORT = 8080

    fun getServerPort(): Int {
        return env.get("PORT")?.toInt() ?: DEFAULT_PORT
    }

    fun getDatabaseURL(): String? {
        return env.get("DB_URL")
    }

    fun getDatabaseName(): String? {
        return env.get("DB_NAME")
    }
    
}

