package org.github.mbarberot.mtg.grimoire.api.business.searches

data class SearchMetadata(
        val resultsCount: Long,
        val pageSize: Int,
        val currentPage: Long,
        val query: String
)