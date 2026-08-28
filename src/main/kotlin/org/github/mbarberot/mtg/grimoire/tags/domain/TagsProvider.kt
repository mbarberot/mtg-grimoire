package org.github.mbarberot.mtg.grimoire.tags.domain

import org.koin.dsl.module

fun provideTags() =
    module {
        single { TagGenerator() }
    }