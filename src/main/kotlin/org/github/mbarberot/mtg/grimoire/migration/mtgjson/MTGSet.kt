package org.github.mbarberot.mtg.grimoire.migration.mtgjson

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import org.github.mbarberot.mtg.grimoire.migration.mtgjson.MTGCard

@JsonIgnoreProperties(ignoreUnknown=true)
data class MTGSet(
        val name: String,
        val code: String,
        val releaseDate: String,
        val type: String,
        val cards: List<MTGCard>
)