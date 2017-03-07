package org.github.mbarberot.mtg.grimoire.view

import org.github.mbarberot.mtg.grimoire.json.JsonEngine
import spark.Response

class JsonView(val response: Response, val mapper: JsonEngine) {
    fun render(function: () -> Any): String {
        response.header("Content-Type", "application/json")
        return mapper.toJson(function.invoke())
    }
}

