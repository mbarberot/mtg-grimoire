package org.github.mbarberot.mtg.grimoire.components.jade.helpers

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
}