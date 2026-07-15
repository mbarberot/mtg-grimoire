package org.github.mbarberot.mtg.grimoire.components.migration.mtgjson

data class MTGCard(
        val uuid: String,
        val multiverseId: Int,
        val name: String,
        val manaCost : String?,
        val power: String?,
        val toughness: String?,
        val type: String,
        val text: String?
)