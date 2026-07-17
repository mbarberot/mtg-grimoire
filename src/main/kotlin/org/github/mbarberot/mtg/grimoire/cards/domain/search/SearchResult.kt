package org.github.mbarberot.mtg.grimoire.cards.domain.search

data class SearchResult<T>(
        val results: Collection<T>,
        val metadata: SearchMetadata
)