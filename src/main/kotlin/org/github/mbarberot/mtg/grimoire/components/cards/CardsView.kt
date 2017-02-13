package org.github.mbarberot.mtg.grimoire.components.cards

import de.neuland.jade4j.JadeConfiguration
import org.github.mbarberot.mtg.grimoire.business.searches.SearchMetadata
import org.github.mbarberot.mtg.grimoire.business.searches.SearchResult
import org.github.mbarberot.mtg.grimoire.components.jade.JadeView

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

    private fun generateLinks(metadata: SearchMetadata): Map<String, Any> {
        val pageCount = getPageCount(metadata)
        val start = Math.max(1, metadata.currentPage - 3)
        val end = Math.min(metadata.currentPage + 3, pageCount)
        
        return mapOf(
                Pair("first", link(metadata.query, 1)),
                Pair("last", link(metadata.query, pageCount)),
                Pair("pages", (start..end).map {
                    link(metadata.query, it, "$it", it == metadata.currentPage)
                })
        )
    }

    private fun link(query: String, page: Long, name: String = "", isCurrentPage: Boolean = false): Any {
        return mapOf(
                Pair("href", "/api/cards?q=$query&page=$page"),
                Pair("name", name),
                Pair("currentPage", isCurrentPage)
        )
    }

    private fun getPageCount(metadata: SearchMetadata) =
            metadata.resultsCount / metadata.pageSize + if (metadata.resultsCount % metadata.pageSize > 0) 1 else 0
}