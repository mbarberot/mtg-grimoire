package org.github.mbarberot.mtg.grimoire.controller

import org.github.mbarberot.mtg.grimoire.view.View

open class AbstractController {
    protected fun render(view: View, templateName: String, varName: String, data: Any) =
            render(view, templateName, mapOf(Pair(varName, data)))

    protected fun render(view: View, templateName: String, data: Map<String,Any>) =
            view.render(data, templateName)
}