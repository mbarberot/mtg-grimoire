package org.github.mbarberot.mtg.grimoire.view.jade.helpers

class ManaHelper {

    fun mana2x(manaCost : String?) : String {
        return convert(manaCost, 2)
    }
    
    fun mana(manaCost: String?) : String {
        return convert(manaCost, 1)
    }
    
    fun convert(manaCost : String?, size : Int) : String {
        return Regex("\\{(\\w)\\}").findAll(manaCost ?: "")
                .map { convertToMana(it.groupValues[1], size) }
                .joinToString(" ")
    }

    fun convertToMana(manaSymbol: String, size: Int): String {
        val sizeClass = if (size > 1 && size < 6) " ms-${size}x" else ""
        return "<i class=\"ms ms-${manaSymbol.toLowerCase()} ms-cost$sizeClass\"/>"
    }
}