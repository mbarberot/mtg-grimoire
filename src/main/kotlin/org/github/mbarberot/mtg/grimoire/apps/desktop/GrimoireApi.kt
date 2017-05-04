package org.github.mbarberot.mtg.grimoire.apps.desktop

import com.github.salomonbrys.kodein.Kodein
import com.github.salomonbrys.kodein.conf.global
import com.github.salomonbrys.kodein.instance
import org.github.mbarberot.mtg.grimoire.core.resources.CardResource

class GrimoireApi {
    val cardResource : CardResource = Kodein.global.instance()
    
    fun sayHello(name: String) = "Hello $name"
}