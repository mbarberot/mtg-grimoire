package org.github.mbarberot.mtg.grimoire.apps.desktop

import javafx.scene.web.WebView
import netscape.javascript.JSObject
import tornadofx.View

class MainView : View() {
    override val root = WebView()

    init {
        with(root) {
            setPrefSize(1000.0, 800.0)
            titleProperty.bind(engine.titleProperty())
            engine.load(MainView::class.java.getResource("/index.html").toURI().toString())

            val window = engine.executeScript("window") as JSObject
            window.setMember("GrimoireApi", GrimoireApi())
        }
    }
}
