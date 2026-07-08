package org.github.mbarberot.mtg.grimoire.components.migration.mtgjson


class TagGenerator {
    fun generateTags(card: MTGCard): Set<String> {
        val rawText = (card.text ?: "").lowercase()
        return buildSet {
            if (rawText.contains("flying")) {
                add("flying")
            }
        }
    }
}