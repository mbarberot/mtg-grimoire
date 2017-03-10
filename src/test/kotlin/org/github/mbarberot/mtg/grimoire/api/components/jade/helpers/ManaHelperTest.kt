package org.github.mbarberot.mtg.grimoire.api.components.jade.helpers

import org.github.mbarberot.mtg.grimoire.api.components.jade.helpers.ManaHelper
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class ManaHelperTest {

    lateinit var manaHelper: ManaHelper

    @Before
    fun setUp() {
        manaHelper = ManaHelper()
    }

    @Test
    fun testManaEmptyCost() {
        assertEquals("", manaHelper.mana(""))
    }

    @Test
    fun testManaSingleCost() {
        assertEquals("<i class=\"ms ms-1 ms-cost\"/>", manaHelper.mana("{1}"))
    }

    @Test
    fun testManaWithSize() {
        assertEquals("<i class=\"ms ms-1 ms-cost ms-2x\"/>", manaHelper.mana2x("{1}"))
    }

    @Test
    fun testManaMultipleCost() {
        assertEquals("" +
                "<i class=\"ms ms-1 ms-cost\"/> " +
                "<i class=\"ms ms-u ms-cost\"/> " +
                "<i class=\"ms ms-w ms-cost\"/>",
                manaHelper.mana("{1}{U}{w}")
        )
    }

    @Test
    fun testManaHybrid() {
        assertEquals("<i class=\"ms ms-ub ms-split ms-cost ms-2x\"/>", manaHelper.mana2x("{U/B}"))
    }

    @Test
    fun testManaPhyrexian() {
        assertEquals("<i class=\"ms ms-up ms-cost ms-2x\"/>", manaHelper.mana2x("{U/P}"))
    }
    
    @Test
    fun testManaColorless() {
        assertEquals("<i class=\"ms ms-c ms-cost ms-2x\"/>", manaHelper.mana2x("{C}"))
    }
}