package com.ajay.androidmodelviewintentsample

import com.ajay.androidmodelviewintentsample.databinding.ActivityMainBinding


/**
 * A class to handle content state updates.
 *
 * Note: This helps keep the receiving Activity simple when handing a state change becomes complicated and also allows
 * easier unit testability
 */
class MviContentHandler(private val binding: ActivityMainBinding) {
    fun handleContent(content: MviState.Content) {
        binding.state = content.stateData
        binding.notifyPropertyChanged(BR.state)
    }
}