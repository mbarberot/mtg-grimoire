package org.github.mbarberot.mtg.grimoire

import org.github.mbarberot.mtg.grimoire.misc.config.Configuration

object Main {
    @JvmStatic fun main(args: Array<String>) {
        App(Configuration()).start()
    }
}

