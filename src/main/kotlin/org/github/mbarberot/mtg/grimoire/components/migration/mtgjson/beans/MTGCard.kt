package org.github.mbarberot.mtg.grimoire.components.migration.mtgjson.beans

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown=true)
data class MTGCard(
        val multiverseid: Int,
        val name: String,
        val manaCost : String?,
        val power: String?,
        val thoughness: String?,
        val type: String,
        val text: String?
)