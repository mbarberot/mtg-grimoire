package org.github.mbarberot.mtg.grimoire.components.migration

data class Version(
        val dbVersion : String,
        val mtgVersion : String
)