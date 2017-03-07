package org.github.mbarberot.mtg.grimoire.models

data class Card(
        val name: String,
        val multiverseId: String,
        val manaCost : String?,
        val set: String,
        val text: String?,
        val power: String?,
        val toughness: String?,
        val type: String,
        val tags: Collection<String>
)