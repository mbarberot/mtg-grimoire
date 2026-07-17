package org.github.mbarberot.mtg.grimoire.components.migration.mtgjson

interface MTGApi {
    fun getVersion(): String
    fun getSets(): List<MTGSet>
    fun getCards(setCode: String): List<MTGCard>
}

data class MTGSet(
    val name: String,
    val code: String,
    private val translations: Map<String,String?>,
) {
    fun getLocalizedName(language: String): String? {
        return translations[language]
    }
}

data class MTGCard(
    val uuid: String,
    val manaCost: String?,
    val power: String?,
    val toughness: String?,
    val setCode: String,
    val foreignData: List<MTGForeignData>,
)

data class MTGForeignData(
    val language: String,
    val multiverseId: Int,
    val name: String,
    val text: String?,
    val type: String?,
)
