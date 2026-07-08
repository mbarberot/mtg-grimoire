package org.github.mbarberot.mtg.grimoire.components.migration.mtgjson

import org.assertj.core.api.Assertions.assertThat
import org.github.mbarberot.mtg.grimoire.components.cards.Card
import org.github.mbarberot.mtg.grimoire.components.cards.InMemoryCardStore
import kotlin.test.Test

fun makeMTGSet(name: String, cardCount: Int = 10): MTGSet {
    return MTGSet(
        name = name,
        code = name.uppercase().substring(0, 3),
        releaseDate = "2026-01-01T12:00:00Z",
        type = "STD",
        cards = buildList {
            IntRange(0, cardCount).forEach { i ->
                add(
                    MTGCard(
                        multiverseid = i,
                        name = "Card $i",
                        manaCost = "{1}{W}{U}",
                        text = "Test card $i",
                        power = "4",
                        toughness = "5",
                        type = "Creature"
                    )
                )
            }
        },
    )
}

class CardUpdaterTest {

    @Test
    fun testLoadCards() {
        val set = makeMTGSet("Test Set", 10)
        val tagGenerator = TagGenerator()
        val cardStore = InMemoryCardStore()

        CardUpdater(cardStore, tagGenerator).updateCards(listOf(set))

        assertThat(cardStore.getCardById("1"))
            .isEqualTo(
                Card(
                    "1",
                    "Test Set",
                    "Card 1",
                    "Creature",
                    "{1}{W}{U}",
                    "Test card 1",
                    "4",
                    "5",
                    emptySet()
                )
            )
    }
}

