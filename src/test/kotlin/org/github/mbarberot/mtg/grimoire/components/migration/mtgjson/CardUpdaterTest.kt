package org.github.mbarberot.mtg.grimoire.components.migration.mtgjson

import com.nhaarman.mockito_kotlin.doReturn
import com.nhaarman.mockito_kotlin.mock
import org.github.mbarberot.mtg.grimoire.components.cards.Card
import org.github.mbarberot.mtg.grimoire.components.cards.CardStore
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.times
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner
import java.util.Collections.singletonList

@RunWith(MockitoJUnitRunner::class)
class CardUpdaterTest {
    lateinit var updater: CardUpdater

    @Mock
    lateinit var cardStore: CardStore

    @Before
    fun setUp() {
        updater = CardUpdater(cardStore)
    }

    @Test
    fun testLoadCards() {
        val card = mock<MTGCard> {
            on { name } doReturn "foo"
            on { multiverseid } doReturn 111222
            on { manaCost } doReturn "{1}{W}{U}"
            on { text } doReturn "Some text"
            on { power } doReturn "4"
            on { toughness } doReturn "5"
            on { type } doReturn "Creature"
        }

        val set = mock<MTGSet> {
            on { name } doReturn "Kaladesh"
            on { cards } doReturn singletonList(card)
        }

        updater.updateCards(listOf(set))

        verify(cardStore, times(1)).addCard(Card(
                "foo",
                "111222",
                "{1}{W}{U}",
                "Kaladesh",
                "Some text",
                "4",
                "5",
                "Creature",
                emptyList()
        ))
    }
}

