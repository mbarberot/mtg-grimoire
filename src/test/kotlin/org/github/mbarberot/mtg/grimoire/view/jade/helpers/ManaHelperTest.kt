package org.github.mbarberot.mtg.grimoire.view.jade.helpers

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
        assertEquals("<i class=\"ms ms-1 ${manaHelper.options}\"/>", manaHelper.mana("{1}"))
    }

    @Test
    fun testManaMultipleCost() {
        assertEquals("" +
                "<i class=\"ms ms-1 ${manaHelper.options}\"/> " +
                "<i class=\"ms ms-u ${manaHelper.options}\"/> " +
                "<i class=\"ms ms-w ${manaHelper.options}\"/>",
                manaHelper.mana("{1}{U}{w}")
        )
    }
}