package org.github.mbarberot.mtg.grimoire.misc.config


class Environment {
    fun get(varName: String): String? {
        return System.getenv(varName)
    }
}