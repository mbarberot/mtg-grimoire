package org.github.mbarberot.mtg.grimoire.core.models

data class Card(
        val id: String,
        val name: String,
        val manaCost: String?,
        val set: String,
        val text: String?,
        val power: String?,
        val toughness: String?,
        val type: String,
        val tags: Collection<String>
)