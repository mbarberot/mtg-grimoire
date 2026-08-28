package factories

import org.github.mbarberot.mtg.grimoire.cards.domain.Card
import org.github.mbarberot.mtg.grimoire.cards.domain.Set

object CardFactory {

    fun makeCards(length: Int): List<Card> {
        return buildList {
            for (i in 1..length) {
                add(Card(
                    multiverseId = "$i",
                    name = "Card $i",
                    set = "Test Set",
                    type = "Creature",
                    manaCost = "{1}{W}{U}",
                    power = "4",
                    toughness = "5",
                    text = "Test card $i",
                ))
            }
        }
    }

    fun makeSet(name: String): Set {
        return Set(
            name = name,
            code = name.uppercase().substring(0,3)
        )
    }

}