package org.github.mbarberot.mtg.grimoire

const val DEFAULT_HOST = "127.0.0.1"
const val DEFAULT_PORT = 8080

data class AppConfig(
    val host: String = DEFAULT_HOST,
    val port: Int = DEFAULT_PORT,
    val devMode: Boolean = false,
    val devRoot: String = "",
    val language: String = "French",
    val userStorage: String
)