package org.github.mbarberot.mtg.grimoire.components.migration.mtgjson

import org.github.mbarberot.mtg.grimoire.mtgapi.api.MTGForeignData


class TagGenerator {
    fun generateTags(texts: MTGForeignData): Set<String> {
        val text = texts.text?.lowercase() ?: ""
        return buildSet {
            if (text.contains("vol")) {
                add("vol")
            }
        }
    }
}