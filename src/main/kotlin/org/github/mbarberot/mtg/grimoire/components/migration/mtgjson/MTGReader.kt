package org.github.mbarberot.mtg.grimoire.components.migration.mtgjson

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import org.github.mbarberot.mtg.grimoire.components.migration.mtgjson.beans.MTGSet
import java.io.InputStream

class MTGReader(
        val inputStream: InputStream,
        val mapper: ObjectMapper = jacksonObjectMapper()
) {
    fun read(): List<MTGSet> {
        inputStream.use { stream -> return mapper.readValue(inputStream) }
    }
}