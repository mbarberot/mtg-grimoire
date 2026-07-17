package org.github.mbarberot.mtg.grimoire.components.migration.mtgjson

import org.assertj.core.api.Assertions.assertThat
import org.github.mbarberot.mtg.grimoire.AppConfig
import org.github.mbarberot.mtg.grimoire.components.cards.Card
import org.github.mbarberot.mtg.grimoire.components.cards.InMemoryCardStore
import java.util.UUID
import kotlin.test.Test

fun makeMTGCards(cardCount: Int = 10): List<MTGCard> {
    return buildList {
        IntRange(1, cardCount).forEach { i ->
            add(
                MTGCard(
                    uuid = UUID.randomUUID().toString(),
                    manaCost = "{1}{W}{U}",
                    power = "4",
                    toughness = "5",
                    setCode = "Test",
                    foreignData = listOf(
                        MTGForeignData(
                            language = "French",
                            multiverseId = i,
                            name = "Card $i",
                            text = "Test card $i",
                            type = "Creature",
                        )
                    )
                )
            )
        }
    }
}

fun makeMTGSet(name: String): MTGSet {
    return MTGSet(
        name = name,
        code = name.uppercase().substring(0, 3),
        translations = mapOf(
            Pair("French", name)
        )
    )
}

class CardUpdaterTest {

    @Test
    fun testLoadCards() {
        val cards = makeMTGCards(10)
        val tagGenerator = TagGenerator()
        val cardStore = InMemoryCardStore()

        CardUpdater(AppConfig(), cardStore, tagGenerator).updateCards(cards)

        assertThat(cardStore.getCardById("1"))
            .isEqualTo(
                Card(
                    "1",
                    "Test",
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

