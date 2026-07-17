package org.github.mbarberot.mtg.grimoire.templating.handlebars.helpers

import com.github.jknack.handlebars.Helper
import com.github.jknack.handlebars.Options
import org.github.mbarberot.mtg.grimoire.templating.mana.ManaConverter

class ManaHelper : Helper<String> {

    private val manaConverter: ManaConverter = ManaConverter()

    override fun apply(context: String?, options: Options?): String {
        return manaConverter.mana(context)
    }
}