package org.github.mbarberot.mtg.grimoire.view.jade.helpers

class ManaHelper {
    val options = "ms-cost ms-2x"

    fun mana(manaCost : String?) : String {
        return Regex("\\{(\\w)\\}").findAll(manaCost ?: "")
                .map { convertToMana(it.groupValues[1]) }
                .joinToString(" ")
    }

    fun convertToMana(manaSymbol: String): String {
        return "<i class=\"ms ms-${manaSymbol.toLowerCase()} $options\"/>"
    }
}