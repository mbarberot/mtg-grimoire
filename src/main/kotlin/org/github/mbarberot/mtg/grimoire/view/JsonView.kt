package org.github.mbarberot.mtg.grimoire.view

import org.github.mbarberot.mtg.grimoire.json.JsonEngine
import spark.Response

class JsonView(private val response: Response, private val mapper: JsonEngine) {
    fun render(function: () -> Any): String {
        response.header("Content-Type", "application/json")
        return mapper.toJson(function.invoke())
    }
}

