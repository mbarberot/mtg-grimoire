package org.github.mbarberot.mtg.grimoire.misc.config


class Configuration(private val env: Environment = Environment()) {
    private val DEFAULT_PORT = 8080

    fun getServerPort(): Int = env.get("PORT")?.toInt() ?: DEFAULT_PORT
    fun getDatabaseURL(): String? = env.get("DB_URL")
    fun getDatabaseName(): String? = env.get("DB_NAME")
}

