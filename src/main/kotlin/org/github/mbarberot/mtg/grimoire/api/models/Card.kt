package org.github.mbarberot.mtg.grimoire.api.models

import org.jongo.marshall.jackson.oid.MongoObjectId

data class Card(
        @MongoObjectId
        val _id: String?,

        val id: String,
        val name: String,
        val manaCost: String?,
        val set: String,
        val text: String?,
        val power: String?,
        val toughness: String?,
        val type: String,
        val tags: Collection<String>
)