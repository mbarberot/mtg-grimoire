package org.github.mbarberot.mtg.grimoire.components.migration.mtgjson.beans

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import org.github.mbarberot.mtg.grimoire.components.migration.mtgjson.beans.MTGCard

@JsonIgnoreProperties(ignoreUnknown=true)
data class MTGSet(
        val name: String,
        val code: String,
        val releaseDate: String,
        val type: String,
        val cards: List<MTGCard>
)