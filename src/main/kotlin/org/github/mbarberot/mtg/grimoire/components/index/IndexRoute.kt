package org.github.mbarberot.mtg.grimoire.components.index

import com.github.salomonbrys.kodein.Kodein
import com.github.salomonbrys.kodein.conf.global
import com.github.salomonbrys.kodein.instance
import org.github.mbarberot.mtg.grimoire.components.index.IndexView
import spark.Request
import spark.Response
import spark.Route

class IndexRoute : Route {
    override fun handle(request: Request, response: Response): String {
        return IndexView(Kodein.global.instance()).render()
    }
}