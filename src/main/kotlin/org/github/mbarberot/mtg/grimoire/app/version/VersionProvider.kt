package org.github.mbarberot.mtg.grimoire.app.version

import org.github.mbarberot.mtg.grimoire.app.version.domain.MigrationRunner
import org.github.mbarberot.mtg.grimoire.app.version.storage.api.VersionStore
import org.github.mbarberot.mtg.grimoire.app.version.storage.impl.InMemoryVersionStore
import org.koin.dsl.module

fun provideVersionModule() = module {
    single<VersionStore> { InMemoryVersionStore() }
    single { MigrationRunner(get(), get()) }
}