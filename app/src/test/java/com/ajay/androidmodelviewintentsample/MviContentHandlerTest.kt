package com.ajay.androidmodelviewintentsample

import com.ajay.androidmodelviewintentsample.databinding.ActivityMainBinding
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import me.danlowe.simplemvi.databinding.ActivityMainBinding
import org.junit.Test
import kotlin.text.Typography.times

class MviContentHandlerTest {

    @Test
    fun `handleContent happy path`() {
        val mockBinding: ActivityMainBinding = mock()
        val ut = MviContentHandler(mockBinding)

        val fakeContentData = MviStateData("1", "4")
        val fakeContent = MviState.Content(fakeContentData)

        ut.handleContent(fakeContent)

        verify(mockBinding, times(1)).state = fakeContentData
        verify(mockBinding, times(1)).notifyPropertyChanged(any())
    }
}