package com.ajay.androidmodelviewintentsample

import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class MviActorTest {

    @Test
    fun `addClicked happy path`() {
        val helper = MviActorTestHelper()
        val expectedValue = "testValue"

        val ut = MviActor(helper::takeAction)

        ut.addClicked(expectedValue)

        assertEquals(1, helper.timesClicked)
        val lastValue = helper.lastValue as? MviAction.AddValue
        assertTrue(helper.lastValue is MviAction.AddValue)
        assertEquals(expectedValue, lastValue!!.value)
    }

    class MviActorTestHelper {
        var lastValue: MviAction? = null
        var timesClicked = 0

        fun takeAction(action: MviAction) {
            timesClicked++
            lastValue = action
        }
    }
}