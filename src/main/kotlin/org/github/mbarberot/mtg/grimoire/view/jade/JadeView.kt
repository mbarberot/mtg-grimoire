package org.github.mbarberot.mtg.grimoire.view.jade

import de.neuland.jade4j.JadeConfiguration

abstract class JadeView(val jade: JadeConfiguration) {
    protected fun render(data: Map<String, Any>, templateName: String): String = jade.renderTemplate(jade.getTemplate(templateName), data)
}