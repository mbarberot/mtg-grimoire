package org.github.mbarberot.mtg.grimoire.migration.mtgjson

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import java.io.InputStreamReader
import java.net.URL

class MTGApi(
        val mapper: ObjectMapper = jacksonObjectMapper()
) {
    fun getSets(): List<MTGSet> =
            URL("http://mtgjson.com/json/AllSetsArray.json").openStream().use { stream ->
                return mapper.readValue(stream)
            }

    fun getVersion(): String =
            URL("http://mtgjson.com/json/version.json").openStream().use { stream ->
                return InputStreamReader(stream).readText().replace("\"", "")
            }

}