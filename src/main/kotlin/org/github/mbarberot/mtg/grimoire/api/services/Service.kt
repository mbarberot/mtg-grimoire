package org.github.mbarberot.mtg.grimoire.api.services

import com.github.salomonbrys.kodein.Kodein

interface Service {
    fun init()
    fun registerModule() : Kodein.Module
}