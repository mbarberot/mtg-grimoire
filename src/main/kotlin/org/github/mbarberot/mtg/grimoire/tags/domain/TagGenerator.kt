package org.github.mbarberot.mtg.grimoire.tags.domain

import org.github.mbarberot.mtg.grimoire.cards.domain.Card


class TagGenerator {
    fun generateTags(card: Card): Set<String> {
        val text = card.text?.lowercase() ?: ""
        return buildSet {
            if (text.contains("vol")) {
                add("vol")
            }
        }
    }
}