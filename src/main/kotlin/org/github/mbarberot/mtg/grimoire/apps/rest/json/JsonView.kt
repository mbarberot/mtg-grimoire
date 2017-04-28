package org.github.mbarberot.mtg.grimoire.apps.rest.json

import spark.Response

class JsonView(private val response: Response, private val mapper: JsonEngine) {
    fun render(function: () -> Any): String {
        response.header("Content-Type", "application/json")
        return mapper.toJson(function.invoke())
    }
}

