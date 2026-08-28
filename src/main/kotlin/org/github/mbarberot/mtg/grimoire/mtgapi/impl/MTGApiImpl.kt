package org.github.mbarberot.mtg.grimoire.mtgapi.impl

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import org.github.mbarberot.mtg.grimoire.cards.domain.Card
import org.github.mbarberot.mtg.grimoire.cards.domain.Set
import org.github.mbarberot.mtg.grimoire.app.config.AppConfig
import org.github.mbarberot.mtg.grimoire.app.config.CardLanguage
import org.github.mbarberot.mtg.grimoire.mtgapi.api.MTGApi
import org.github.mbarberot.mtg.grimoire.mtgapi.impl.converters.Converter
import org.github.mbarberot.mtg.grimoire.mtgapi.impl.converters.MTGCardConverter
import org.github.mbarberot.mtg.grimoire.mtgapi.impl.converters.MTGSetConverter
import java.net.URI

class MTGApiImpl(
    val baseUrl: String,
    val mapper: ObjectMapper = jacksonObjectMapper(),
    val appConfig: AppConfig,
) : MTGApi {

    val setConverter: Converter<MTGSet, Set> = MTGSetConverter(appConfig.language)
    val cardConverter: Converter<MTGCard, Card> = MTGCardConverter(appConfig.language)

    override fun getVersion(): String =
        URI.create("$baseUrl/api/v5/Meta.json")
            .toURL()
            .openStream()
            .use { stream ->
                val meta = mapper.readValue<MTGJson<MTGVersion>>(stream)
                return meta.data.version
            }

    override fun getSets(): List<Set> =
        URI.create("$baseUrl/api/v5/SetList.json")
            .toURL()
            .openStream()
            .use { stream ->
                val sets = mapper.readValue<MTGJson<List<MTGSet>>>(stream)
                return@use sets.data
            }
            .map { set -> setConverter.convert(set) }

    override fun getCards(setCode: String): List<Card> =
        URI.create("$baseUrl/api/v5/${setCode.uppercase()}.json")
            .toURL()
            .openStream()
            .use { stream ->
                val set = mapper.readValue<MTGJson<MTGDataSetWithCards>>(stream)
                return@use set.data.cards
            }
            .map { card -> cardConverter.convert(card) }

}

data class MTGJson<T>(val data: T)
data class MTGVersion(val version: String)
data class MTGDataSetWithCards(val cards: List<MTGCard>)
data class MTGSet(
    val name: String,
    val code: String,
    private val translations: Map<String, String?>,
) {
    fun getLocalizedName(language: CardLanguage): String? {
        return translations[language.value]
    }
}

data class MTGCard(
    val uuid: String,
    val name: String,
    val text: String?,
    val type: String,
    val manaCost: String?,
    val power: String?,
    val toughness: String?,
    val setCode: String,
    val foreignData: List<MTGForeignData>,
    val identifiers: MTGIdentifiers,
)

data class MTGIdentifiers(
    val multiverseId: String,
)

data class MTGForeignData(
    val language: String,
    val identifiers: MTGIdentifiers,
    val name: String,
    val text: String?,
    val type: String?,
)
