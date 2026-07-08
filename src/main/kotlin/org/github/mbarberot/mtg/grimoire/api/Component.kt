package org.github.mbarberot.mtg.grimoire.api

import io.javalin.config.RoutesConfig
import org.koin.core.module.Module

interface Component {
    fun initialize(): Module?
    fun declareRoutes(routes: RoutesConfig)
}