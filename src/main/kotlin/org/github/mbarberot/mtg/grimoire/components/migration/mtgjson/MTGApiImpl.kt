package org.github.mbarberot.mtg.grimoire.components.migration.mtgjson

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import java.net.URI

class MTGApiImpl(
    val baseUrl: String,
    val mapper: ObjectMapper = jacksonObjectMapper(),
) : MTGApi {

    override fun getVersion(): String =
        URI.create("$baseUrl/api/v5/Meta.json")
            .toURL()
            .openStream()
            .use { stream ->
                val meta = mapper.readValue<MTGJson<MTGVersion>>(stream)
                return meta.data.version
            }

    override fun getSets(): List<MTGSet> =
        URI.create("$baseUrl/api/v5/SetList.json")
            .toURL()
            .openStream()
            .use { stream ->
                val sets = mapper.readValue<MTGJson<List<MTGSet>>>(stream)
                return sets.data
            }

    override fun getCards(setCode: String): List<MTGCard> {
        URI.create("$baseUrl/api/v5/${setCode.uppercase()}.json")
            .toURL()
            .openStream()
            .use { stream ->
                val set = mapper.readValue<MTGJson<MTGDataSetWithCards>>(stream)
                return set.data.cards
            }
    }
}

data class MTGJson<T>(val data: T)

data class MTGVersion(val version: String)
data class MTGDataSetWithCards(val cards: List<MTGCard>)