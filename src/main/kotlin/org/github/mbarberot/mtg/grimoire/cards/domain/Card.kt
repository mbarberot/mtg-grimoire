package org.github.mbarberot.mtg.grimoire.cards.domain

data class Card(
    val multiverseId: String,
    val set: String,
    val name: String,
    val type: String,
    val manaCost: String? = null,
    val text: String? = null,
    val power: String? = null,
    val toughness: String? = null,
    val tags: Set<String> = setOf()
)