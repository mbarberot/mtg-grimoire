package org.github.mbarberot.mtg.grimoire.view

import de.neuland.jade4j.JadeConfiguration

class View(val jade: JadeConfiguration = JadeConfiguration()) {
    init {
        jade.templateLoader = GrimoireTemplateLoader("/templates/")
        jade.isPrettyPrint = true
    }
    
    fun render(data: Map<String, Any>, templateName: String): Any = jade.renderTemplate(jade.getTemplate(templateName), data)
}