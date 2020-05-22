package com.ajay.androidmodelviewintentsample

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import org.junit.Test

import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule

class MviViewModelTest {

    @get:Rule
    var rule = InstantTaskExecutorRule()

    private lateinit var ut: MviViewModel

    @Before
    fun setup() {
        ut = MviViewModel()
    }

    @Test
    fun `ensure initialized state`() {
        assertEquals(MviStateData(), (ut.state.value as? MviState.Content)?.stateData)
    }

    @Test
    fun `take action happy path`() {
        val expectedValue = "5"

        val actionOne = MviAction.AddValue("2")
        val actionTwo = MviAction.AddValue("3")

        ut.takeAction(actionOne)
        ut.takeAction(actionTwo)

        assertEquals(expectedValue, (ut.state.value as? MviState.Content)?.stateData?.currentValue)
    }
}