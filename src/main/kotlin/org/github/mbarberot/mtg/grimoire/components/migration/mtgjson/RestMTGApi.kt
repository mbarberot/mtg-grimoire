package org.github.mbarberot.mtg.grimoire.components.migration.mtgjson

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import java.io.InputStreamReader
import java.net.URL

class RestMTGApi(
        val mapper: ObjectMapper = jacksonObjectMapper()
) : MTGApi {

    override fun getSets(): List<MTGSet> =
            URL("http://mtgjson.com/json/AllSetsArray.json").openStream().use { stream ->
                return mapper.readValue(stream)
            }

    override fun getVersion(): String =
            URL("http://mtgjson.com/json/version.json").openStream().use { stream ->
                return InputStreamReader(stream).readText().replace("\"", "")
            }

}