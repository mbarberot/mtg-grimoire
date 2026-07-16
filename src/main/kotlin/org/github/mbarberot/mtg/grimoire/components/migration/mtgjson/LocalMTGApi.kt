package org.github.mbarberot.mtg.grimoire.components.migration.mtgjson

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import org.github.mbarberot.mtg.grimoire.AppConfig
import java.net.URI

class LocalMTGApi(
    val baseDir: String,
    val mapper: ObjectMapper = jacksonObjectMapper(),
) : MTGApi {

    override fun getSets(): List<MTGSet> =
        URI.create("file:///$baseDir/tmp/Sets.test.json")
            .toURL()
            .openStream()
            .use { stream ->
                val sets = mapper.readValue<MTGSets>(stream)
                return sets.data.values.toList()
            }

    override fun getVersion(): String = "1.0.0"

}