package org.github.mbarberot.mtg.grimoire.app.version.domain

interface Upgrader {
    fun upgrade(startVersion: Version): Version
}