package org.github.mbarberot.mtg.grimoire.cards

import org.github.mbarberot.mtg.grimoire.cards.domain.search.CardSearch
import org.github.mbarberot.mtg.grimoire.cards.server.CardView
import org.github.mbarberot.mtg.grimoire.cards.server.CardsView
import org.github.mbarberot.mtg.grimoire.cards.server.GetCardRoute
import org.github.mbarberot.mtg.grimoire.cards.server.GetCardsRoute
import org.koin.dsl.module

fun provideCardModule() = module {
    // Services
    single { CardSearch(get()) }

    // Routes & Views
    single { CardView(get()) }
    single { CardsView(get()) }
    single { GetCardRoute(get(), get()) }
    single { GetCardsRoute(get(), get()) }
}