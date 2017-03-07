package org.github.mbarberot.mtg.grimoire.migration.mtgjson

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown=true)
data class MTGCard(
        val multiverseid: Int,
        val name: String,
        val manaCost : String?,
        val power: String?,
        val toughness: String?,
        val type: String,
        val text: String?
)