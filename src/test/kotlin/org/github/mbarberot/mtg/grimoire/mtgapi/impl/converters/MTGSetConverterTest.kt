package org.github.mbarberot.mtg.grimoire.mtgapi.impl.converters

import org.assertj.core.api.Assertions.assertThat
import org.github.mbarberot.mtg.grimoire.app.config.CardLanguage
import org.github.mbarberot.mtg.grimoire.mtgapi.impl.MTGSet
import org.github.mbarberot.mtg.grimoire.cards.domain.Set
import org.junit.jupiter.api.Test

object MTGFactory {
    fun makeMTGSet(name: String, localizedName: String = name): MTGSet {
        return MTGSet(
            name = name,
            code = name.uppercase().substring(0, 3),
            translations = mapOf(
                Pair("French", localizedName)
            )
        )
    }
}

class MTGSetConverterTest {

    @Test
    fun `convert set with available translation`() {
        // Arrange
        val set = MTGFactory.makeMTGSet("Shadowmoor", "Sombrelande")

        // Act
        val convertedSet = MTGSetConverter(CardLanguage.FRENCH).convert(set);

        // Assert
        assertThat(convertedSet).isEqualTo(
            Set(
                name = "Sombrelande",
                code = "SHA"
            )
        )
    }

    @Test
    fun `convert set with unavailable translation`() {
        // Arrange
        val set = MTGFactory.makeMTGSet("Shadowmoor", "Sombrelande")

        // Act
        val convertedSet = MTGSetConverter(CardLanguage.JAPANESE).convert(set);

        // Assert
        assertThat(convertedSet).isEqualTo(
            Set(
                name = "Shadowmoor",
                code = "SHA"
            )
        )
    }

}