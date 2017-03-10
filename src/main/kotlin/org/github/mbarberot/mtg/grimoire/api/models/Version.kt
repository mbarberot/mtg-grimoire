package org.github.mbarberot.mtg.grimoire.api.models

import org.bson.types.ObjectId

data class Version(
        val _id: ObjectId?,
        val dbVersion: String,
        val mtgVersion: String
)