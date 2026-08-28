package org.github.mbarberot.mtg.grimoire.database

import org.github.mbarberot.mtg.grimoire.app.config.AppConfig
import org.jdbi.v3.core.Jdbi
import org.jdbi.v3.core.kotlin.KotlinPlugin
import org.jdbi.v3.core.kotlin.useHandleUnchecked
import org.jdbi.v3.sqlobject.kotlin.KotlinSqlObjectPlugin
import org.koin.dsl.module
import kotlin.io.path.Path
import kotlin.io.path.createDirectories

fun provideDatabaseStorage() =
    module {
        single { initJdbi(get()) }
    }

fun initJdbi(appConfig: AppConfig): Jdbi {
    val dbPath = "${appConfig.userStorage}/db.h2"
    Path(dbPath).createDirectories()
    val jdbi = Jdbi.create("jdbc:h2:$dbPath:mtg-grimoire;DB_CLOSE_DELAY=-1;AUTO_SERVER=TRUE")
        .installPlugin(KotlinPlugin())
        .installPlugin(KotlinSqlObjectPlugin())

    initDB(jdbi);

    return jdbi;
}

fun initDB(jdbi: Jdbi) {
    val sql = readResource("/db/v1.0.0_structure.sql")
    jdbi.useHandleUnchecked { handle ->
        handle.createScript(sql).execute()
    }
}

private fun readResource(path: String): String? =
    object {}.javaClass.getResource(path)?.readText()
