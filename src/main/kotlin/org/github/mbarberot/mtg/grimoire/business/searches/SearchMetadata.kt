package org.github.mbarberot.mtg.grimoire.business.searches

data class SearchMetadata(
        val resultsCount: Int,
        val pageSize: Int,
        val currentPage: Int,
        val query: String
)