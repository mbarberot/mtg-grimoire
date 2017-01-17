package org.github.mbarberot.mtg.grimoire.business.updates

import com.nhaarman.mockito_kotlin.doReturn
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.whenever
import org.github.mbarberot.mtg.grimoire.business.updates.mtgjson.MTGReader
import org.github.mbarberot.mtg.grimoire.business.updates.mtgjson.beans.MTGCard
import org.github.mbarberot.mtg.grimoire.business.updates.mtgjson.beans.MTGSet
import org.github.mbarberot.mtg.grimoire.model.beans.Card
import org.github.mbarberot.mtg.grimoire.model.managers.CardManager
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
    lateinit var cardManager: CardManager

    @Mock
    lateinit var reader: MTGReader

    @Before
    fun setUp() {
        updater = CardUpdater(cardManager, reader)
    }

    @Test
    fun testLoadCards() {
        val card = mock<MTGCard> {
            on { name } doReturn "foo"
            on { multiverseid } doReturn 111222
        }
        val set = mock<MTGSet> { on { cards } doReturn singletonList(card) }

        doReturn(listOf(set)).whenever(reader).read()

        updater.updateCards()

        verify(cardManager, times(1)).addCard(Card("foo", "111222", emptyList()))
    }
}

