package org.github.mbarberot.mtg.grimoire.mtgapi.impl.converters

import org.assertj.core.api.Assertions.assertThat
import org.github.mbarberot.mtg.grimoire.cards.domain.Card
import org.github.mbarberot.mtg.grimoire.app.config.CardLanguage
import org.github.mbarberot.mtg.grimoire.mtgapi.impl.MTGCard
import org.github.mbarberot.mtg.grimoire.mtgapi.impl.MTGForeignData
import org.github.mbarberot.mtg.grimoire.mtgapi.impl.MTGIdentifiers
import org.junit.jupiter.api.Test
import java.util.*


private fun makeMTGCard(i: Int = 1): MTGCard = MTGCard(
    uuid = UUID.randomUUID().toString(),
    manaCost = "{1}{W}{U}",
    power = "4",
    toughness = "5",
    setCode = "Test",
    name = "Card $i",
    text = "Text $i",
    type = "Creature",
    identifiers = MTGIdentifiers(
        "00${i}"
    ),
    foreignData = listOf(
        MTGForeignData(
            language = "French",
            identifiers = MTGIdentifiers(
                "0${i}0"
            ),
            name = "Carte $i",
            text = "Texte $i",
            type = "Créature",
        )
    )
)

class MTGCardConverterTest {

    @Test
    fun `convert set with available translation`() {
        // Arrange
        val card = makeMTGCard()

        // Act
        val convertedCard = MTGCardConverter(CardLanguage.FRENCH).convert(card);

        // Assert
        assertThat(convertedCard).isEqualTo(
            Card(
                manaCost = "{1}{W}{U}",
                power = "4",
                toughness = "5",
                set = "Test",
                multiverseId = "010",
                name = "Carte 1",
                text = "Texte 1",
                type = "Créature",
            )
        )
    }

    @Test
    fun `convert set with unavailable translation`() {
        // Arrange
        val card = makeMTGCard()

        // Act
        val convertedCard = MTGCardConverter(CardLanguage.JAPANESE).convert(card);

        // Assert
        assertThat(convertedCard).isEqualTo(
            Card(
                manaCost = "{1}{W}{U}",
                power = "4",
                toughness = "5",
                set = "Test",
                multiverseId = "001",
                name = "Card 1",
                text = "Text 1",
                type = "Creature",
            )
        )
    }
}