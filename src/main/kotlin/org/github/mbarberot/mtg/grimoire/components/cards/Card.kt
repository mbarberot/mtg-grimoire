package org.github.mbarberot.mtg.grimoire.components.cards

data class Card(
        val name: String,
        val multiverseId: String,
        val manaCost : String?,
        val set: String,
        val text: String?,
        val power: String?,
        val thoughness: String?,
        val type: String,
        val tags: Collection<String>
)