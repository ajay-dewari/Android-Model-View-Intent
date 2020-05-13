package com.ajay.androidmodelviewintentsample

sealed class MviState {
    class Content(val stateData: MviStateData): MviState()
    object Loading : MviState()

    /**
     * Even signifies something that occurs only once, complimenting the state, but not changing it
     */
    abstract class Effect : MviState()
    object InvalidNumberError : Effect()
}