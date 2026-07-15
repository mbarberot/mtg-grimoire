package org.github.mbarberot.mtg.grimoire.components.cards

import com.github.jknack.handlebars.Handlebars
import org.github.mbarberot.mtg.grimoire.business.searches.SearchMetadata
import org.github.mbarberot.mtg.grimoire.business.searches.SearchResult
import kotlin.math.max
import kotlin.math.min

class CardsView(
    private val handlebars: Handlebars,
) {
    fun render(search: SearchResult<Card>): String {
        return handlebars.compile("parts/search-results")
            .apply(
                mapOf(
                    Pair("cards", search.results),
                    Pair("pagination", generatePagination(search.metadata)),
                )
            )
    }
}

fun generatePagination(metadata: SearchMetadata): Map<String, Any> {
    val pageCount = getPageCount(metadata)
    val start = max(1, metadata.currentPage - 3)
    val end = min(metadata.currentPage + 3, pageCount)

    return mapOf(
        Pair("first", link(metadata.query, 1)),
        Pair("last", link(metadata.query, pageCount)),
        Pair("pages", (start..end).map {
            link(metadata.query, it, "$it", it == metadata.currentPage)
        })
    )
}

private fun link(query: String, page: Int, name: String = "", isCurrentPage: Boolean = false): Any {
    return mapOf(
        Pair("href", "/api/cards?q=$query&page=$page"),
        Pair("name", name),
        Pair("currentPage", isCurrentPage)
    )
}

private fun getPageCount(metadata: SearchMetadata) =
    metadata.resultsCount / metadata.pageSize + if (metadata.resultsCount % metadata.pageSize > 0) 1 else 0