package org.github.mbarberot.mtg.grimoire.cards.domain

import factories.CardFactory
import org.assertj.core.api.Assertions.assertThat
import org.github.mbarberot.mtg.grimoire.cards.storage.impl.InMemoryCardStore
import org.github.mbarberot.mtg.grimoire.tags.domain.TagGenerator
import kotlin.test.Test

class CardUpdaterTest {

    @Test
    fun testLoadCards() {
        val cards = CardFactory.makeCards(10)
        val tagGenerator = TagGenerator()
        val cardStore = InMemoryCardStore()

        CardUpdater(cardStore, tagGenerator).updateCards(cards)

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

