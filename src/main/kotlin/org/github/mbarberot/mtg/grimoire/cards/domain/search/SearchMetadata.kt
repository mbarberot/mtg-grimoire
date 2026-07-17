package org.github.mbarberot.mtg.grimoire.cards.domain.search

data class SearchMetadata(
        val resultsCount: Int,
        val pageSize: Int,
        val currentPage: Int,
        val query: String
)