package com.ajay.androidmodelviewintentsample

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hadilq.liveevent.LiveEvent

class MviViewModel : ViewModel() {
    // keep this private so that only the ViewModel can modify the state
    private val _state = MutableLiveData<MviState>()
        .apply { value = MviState.Content(MviStateData()) }

    // Create a publicly accessible LiveData object that can be observed
    val state: LiveData<MviState> = _state

    // Note same as above, except this is an Effect to be consumed by the view only once
    private val _effect = LiveEvent<MviState.Effect>()
    val effect: LiveData<MviState.Effect> = _effect

    /**
     * Take an [MviAction] and process it
     */
    fun takeAction(action: MviAction) {
        when (action) {
            is MviAction.AddValue -> handleAddValueAction(action.value)
        }
    }

    /**
     * Handle the action of adding a value to the current state.
     *
     * Note: This should cover all outcomes, error or not
     */
    private fun handleAddValueAction(addValue: String?) {
        val newState: MviState = when (val processValue = addValue?.toIntOrNull()) {
            null -> MviState.InvalidNumberError
            else -> {
                val newStateData = currentStateData add processValue
                MviState.Content(newStateData)
            }
        }

        update(newState)
    }

    /**
     * A simple router to simplify the logic of determining whether to update the [_effect] or [_state]
     */
    private fun update(newState: MviState) {
        when (newState) {
            is MviState.Effect -> _effect.postValue(newState)
            is MviState.Loading,
            is MviState.Content -> _state.postValue(newState)
        }
    }

    /**
     * Increment the current state's numeric value by [num]. This simplifies the operation of adding a [String]
     * representation of an [Int] to an [Int] and turning the result back to a [String].
     *
     * Note: This utilizes the copy function, which creates a new immutable data class from the old one. It helps
     * significantly when the state data grows complex
     */
    private infix fun MviStateData.add(num: Int): MviStateData {
        val currentNumber = currentValue.toIntOrNull() ?: 0
        return copy(currentValue = (currentNumber + num).toString())
    }

    /**
     * A helper to get the current state data object if it exists, otherwise returning a default value
     */
    private val currentStateData: MviStateData
        get() {
            val data = _state.value?.let {
                return if (it is MviState.Content) {
                    it.stateData
                } else {
                    MviStateData()
                }
            }

            return data ?: MviStateData()
        }
}