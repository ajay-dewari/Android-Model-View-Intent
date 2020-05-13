package com.ajay.androidmodelviewintentsample

/**
 * Represent and Action taken by the user in the MVI example view
 */
sealed class MviAction {
    /**
     * Action type indicating the user desired to add the given [value] to the current state
     */
    class AddValue(val value: String) : MviAction()
}