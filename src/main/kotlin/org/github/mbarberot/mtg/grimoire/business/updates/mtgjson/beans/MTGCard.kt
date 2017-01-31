package org.github.mbarberot.mtg.grimoire.business.updates.mtgjson.beans

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown=true)
data class MTGCard(
        val multiverseid: Int,
        val name: String,
        val manaCost : String?
)