package org.github.mbarberot.mtg.grimoire.api.business.searches

data class SearchResult(
        val results: Collection<Any>,
        val metadata: SearchMetadata
)