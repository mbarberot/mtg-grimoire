package org.github.mbarberot.mtg.grimoire.components.template.engine.helpers

import org.github.mbarberot.mtg.grimoire.templating.mana.ManaConverter
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

class ManaConverterTest {

    lateinit var manaConverter: ManaConverter

    @BeforeTest
    fun setUp() {
        manaConverter = ManaConverter()
    }

    @Test
    fun testManaEmptyCost() {
        assertEquals("", manaConverter.mana(""))
    }

    @Test
    fun testManaSingleCost() {
        assertEquals("<i class=\"ms ms-1 ms-cost\"></i>", manaConverter.mana("{1}"))
    }

    @Test
    fun testManaWithSize() {
        assertEquals("<i class=\"ms ms-1 ms-cost ms-2x\"></i>", manaConverter.mana2x("{1}"))
    }

    @Test
    fun testManaMultipleCost() {
        assertEquals("" +
                "<i class=\"ms ms-1 ms-cost\"></i> " +
                "<i class=\"ms ms-u ms-cost\"></i> " +
                "<i class=\"ms ms-w ms-cost\"></i>",
                manaConverter.mana("{1}{U}{W}")
        )
    }

    @Test
    fun testManaHybrid() {
        assertEquals("<i class=\"ms ms-ub ms-split ms-cost ms-2x\"></i>", manaConverter.mana2x("{U/B}"))
    }

    @Test
    fun testManaPhyrexian() {
        assertEquals("<i class=\"ms ms-up ms-cost ms-2x\"></i>", manaConverter.mana2x("{U/P}"))
    }
    
    @Test
    fun testManaColorless() {
        assertEquals("<i class=\"ms ms-c ms-cost ms-2x\"></i>", manaConverter.mana2x("{C}"))
    }
}