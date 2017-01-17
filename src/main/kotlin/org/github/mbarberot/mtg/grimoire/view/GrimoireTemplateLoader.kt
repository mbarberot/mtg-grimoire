package org.github.mbarberot.mtg.grimoire.view

import de.neuland.jade4j.template.ClasspathTemplateLoader
import java.io.InputStreamReader
import java.io.Reader

class GrimoireTemplateLoader(val templateRoot : String) : ClasspathTemplateLoader() {
    
    override fun getReader(name: String): Reader {
        return InputStreamReader(javaClass.getResourceAsStream(getName(name)))
    }

    private fun getName(name: String): String = templateRoot + name
}