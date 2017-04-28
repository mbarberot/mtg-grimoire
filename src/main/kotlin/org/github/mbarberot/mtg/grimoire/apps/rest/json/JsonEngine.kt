package org.github.mbarberot.mtg.grimoire.apps.rest.json

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper

class JsonEngine(private val mapper: ObjectMapper = jacksonObjectMapper()) {
    init {
        mapper.enable(SerializationFeature.INDENT_OUTPUT)
    }

    fun toJson(obj: Any): String = mapper.writeValueAsString(obj)
}