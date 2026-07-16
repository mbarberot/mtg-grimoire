package org.github.mbarberot.mtg.grimoire.components.migration.mtgjson

data class MTGCard(
    val uuid: String,
    val manaCost: String?,
    val power: String?,
    val toughness: String?,
    val type: String,
    val foreignData: List<MTGForeignData>,
)

data class MTGForeignData(
    val language: String,
    val multiverseId: Int,
    val name: String,
    val text: String?,
    val type: String?,
)