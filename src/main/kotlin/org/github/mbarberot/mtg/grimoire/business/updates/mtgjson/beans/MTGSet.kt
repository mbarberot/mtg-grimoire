package org.github.mbarberot.mtg.grimoire.business.updates.mtgjson.beans

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import org.github.mbarberot.mtg.grimoire.business.updates.mtgjson.beans.MTGCard

@JsonIgnoreProperties(ignoreUnknown=true)
data class MTGSet(
        val name: String,
        val code: String,
        val releaseDate: String,
        val type: String,
        val cards: List<MTGCard>
)