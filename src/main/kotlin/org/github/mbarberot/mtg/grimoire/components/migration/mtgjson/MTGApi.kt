package org.github.mbarberot.mtg.grimoire.components.migration.mtgjson

interface MTGApi {
    fun getSets(): List<MTGSet>
    fun getVersion(): String
}