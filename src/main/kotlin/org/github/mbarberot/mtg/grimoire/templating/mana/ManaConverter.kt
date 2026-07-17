package org.github.mbarberot.mtg.grimoire.templating.mana

class ManaConverter {

    fun mana2x(manaCost: String?): String {
        return convert(manaCost, 2)
    }

    fun mana(manaCost: String?): String {
        return convert(manaCost, 1)
    }

    fun convert(manaCost: String?, size: Int): String {
        return Regex("\\{([0-9wrbgupc/]+)\\}")
            .findAll(manaCost?.lowercase() ?: "")
            .joinToString(" ") { convertToMana(it.groupValues[1], size) }
    }

    fun convertToMana(manaSymbol: String, size: Int): String {
        val classes = StringBuilder()

        classes.append("ms ms-${manaSymbol.replace("/", "")}")

        if (isHybrid(manaSymbol)) {
            classes.append(" ms-split")
        }
        
        classes.append(" ms-cost")
        
        if (size in 2..<6) {
            classes.append(" ms-${size}x")
        }
        
        return "<i class=\"$classes\"></i>"
    }

    private fun isHybrid(manaSymbol: String) = 
            manaSymbol.contains("/") 
                    && !manaSymbol.contains("p")
                    
}