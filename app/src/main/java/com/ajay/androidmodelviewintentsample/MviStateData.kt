package com.ajay.androidmodelviewintentsample

/**
 * Data class representing the state of the view. Any persistent state should be annotated here
 *
 * @param currentValue The current numeric value
 * @param toAddValue Default value to be added to the current value.
 */
data class MviStateData(
    val currentValue: String = "0",
    val toAddValue: String = ""
)
