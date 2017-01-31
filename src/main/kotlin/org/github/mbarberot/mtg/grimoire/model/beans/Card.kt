package org.github.mbarberot.mtg.grimoire.model.beans

data class Card(
        val name: String,
        val multiverseId: String,
        val manaCost : String,
        val tags: Collection<String>
)