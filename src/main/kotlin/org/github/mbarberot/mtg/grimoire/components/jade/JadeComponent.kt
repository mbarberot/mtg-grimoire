package org.github.mbarberot.mtg.grimoire.components.jade

import com.github.salomonbrys.kodein.Kodein
import com.github.salomonbrys.kodein.bind
import com.github.salomonbrys.kodein.conf.global
import com.github.salomonbrys.kodein.instance
import de.neuland.jade4j.JadeConfiguration
import org.github.mbarberot.mtg.grimoire.api.Component
import org.github.mbarberot.mtg.grimoire.components.jade.helpers.ManaHelper

class JadeComponent : Component {
    override fun initialize() {
        Kodein.global.addImport(Kodein.Module {
            bind<JadeConfiguration>() with instance(initializeJadeEngine())
        })
    }

    fun initializeJadeEngine(): JadeConfiguration {
        val jade = JadeConfiguration()
        jade.templateLoader = GrimoireTemplateLoader("/templates/")
        jade.isPrettyPrint = true
        jade.sharedVariables = mapOf(Pair("mana", ManaHelper()))
        return jade
    }
}