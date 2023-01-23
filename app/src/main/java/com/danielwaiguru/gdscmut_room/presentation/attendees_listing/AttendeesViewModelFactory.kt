package com.danielwaiguru.gdscmut_room.presentation.attendees_listing

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.danielwaiguru.gdscmut_room.data.AttendeesRepository

@Suppress("UNCHECKED_CAST")
class AttendeesViewModelFactory(
    private val attendeesRepository: AttendeesRepository
): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AttendeesViewModel::class.java)) {
            return AttendeesViewModel(attendeesRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}