package org.github.mbarberot.mtg.grimoire.mtgapi.impl.converters

interface Converter<FROM, TO> {
    fun convert(data: FROM): TO
}