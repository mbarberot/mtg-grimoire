package org.github.mbarberot.mtg.grimoire.app.config

import org.github.mbarberot.mtg.grimoire.app.config.CardLanguage.FRENCH

const val LOCALHOST = "127.0.0.1"
const val DEFAULT_PORT = 8080

data class AppConfig(
    val host: String = LOCALHOST,
    val port: Int = DEFAULT_PORT,
    val devMode: Boolean = false,
    val devRoot: String = "",
    val language: CardLanguage = FRENCH,
    val userStorage: String
)