package org.github.mbarberot.mtg.grimoire.components.cards

import de.neuland.jade4j.JadeConfiguration
import org.github.mbarberot.mtg.grimoire.business.searches.SearchMetadata
import org.github.mbarberot.mtg.grimoire.business.searches.SearchResult
import org.github.mbarberot.mtg.grimoire.view.beans.Link
import org.github.mbarberot.mtg.grimoire.view.beans.Pagination
import org.github.mbarberot.mtg.grimoire.view.jade.JadeView

class CardsView(jade: JadeConfiguration) : JadeView(jade) {
    fun render(search: SearchResult): String {
        return render(
                mapOf(
                        Pair("cards", search.results),
                        Pair("metadata", generateMetadata(search.metadata))
                ),
                "parts/search-results"
        )
    }

    private fun generateMetadata(metadata: SearchMetadata): Map<String, Any> {
        return mapOf(
                Pair("pagination", generateLinks(metadata))
        )
    }

    private fun generateLinks(metadata: SearchMetadata): Pagination {
        val pageCount = getPageCount(metadata)
        val start = Math.max(1, metadata.currentPage - 3)
        val end = Math.min(metadata.currentPage + 3, pageCount)

        return Pagination(
                Link("/api/cards?q=${metadata.query}&page=1", "", false),
                Link("/api/cards?q=${metadata.query}&page=$pageCount", "", false),
                (start..end).map { Link("/api/cards?q=${metadata.query}&page=$it", "$it", it == metadata.currentPage) }
        )
    }

    private fun getPageCount(metadata: SearchMetadata) =
            metadata.resultsCount / metadata.pageSize + if (metadata.resultsCount % metadata.pageSize > 0) 1 else 0
}