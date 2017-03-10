package org.github.mbarberot.mtg.grimoire.api.migration.mtgjson

import java.util.*


class TagGenerator {
    fun generateTags(card: MTGCard) : List<String> {
        val tags : ArrayList<String> = ArrayList()
        
        generateAbilityTags(tags, card.text ?: "")
        
        return tags
    }

    private fun generateAbilityTags(tags: ArrayList<String>, rawText: String) {
        val text = rawText.toLowerCase()
        if(text.contains("flying")) tags.add("flying")
    }
}