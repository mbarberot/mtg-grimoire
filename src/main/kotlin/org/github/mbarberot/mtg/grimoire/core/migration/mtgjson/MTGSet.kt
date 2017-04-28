package org.github.mbarberot.mtg.grimoire.core.migration.mtgjson

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown=true)
data class MTGSet(
        val name: String,
        val code: String,
        val releaseDate: String,
        val type: String,
        val cards: List<MTGCard>
)