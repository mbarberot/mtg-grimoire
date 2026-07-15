package org.github.mbarberot.mtg.grimoire.business.searches

data class SearchResult<T>(
        val results: Collection<T>,
        val metadata: SearchMetadata
)