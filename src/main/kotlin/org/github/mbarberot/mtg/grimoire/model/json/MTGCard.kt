package org.github.mbarberot.mtg.grimoire.model.json

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown=true)
data class MTGCard(
        val multiverseid: Int,
        val name: String
)