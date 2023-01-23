package com.danielwaiguru.gdscmut_room.presentation.attendees_listing

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.danielwaiguru.gdscmut_room.data.Attendee
import com.danielwaiguru.gdscmut_room.data.AttendeesRepository
import com.danielwaiguru.gdscmut_room.utils.TextFieldState
import com.danielwaiguru.gdscmut_room.utils.validateLength
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class AttendeesViewModel(
    private val attendeesRepository: AttendeesRepository,
): ViewModel() {

    val allAttendees: StateFlow<List<Attendee>> =
        attendeesRepository.getAttendees()
            .stateIn(
                viewModelScope,
                SharingStarted.WhileSubscribed(),
                listOf()
            )
    private val attendeeUIState: MutableStateFlow<AttendeeForm?> =
        MutableStateFlow(null)
    val attendeeFormUIState: StateFlow<AttendeeForm> get() =
        attendeeUIState.map { attendee ->
            AttendeeForm(
                firstName = attendee?.firstName ?: TextFieldState(),
                lastName = attendee?.lastName ?: TextFieldState(),
                age = attendee?.age ?: TextFieldState()
            )
        }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), AttendeeForm())
    fun onFirstNameChange(newValue: String) {
        attendeeUIState.update { currentState ->
            currentState?.copy(
                firstName = TextFieldState(
                    content = newValue,
                    errorMessage = newValue.validateLength()
                ),
            )
        }
    }
    fun onLastNameChange(newValue: String) {
        attendeeUIState.update { currentState ->
            currentState?.copy(
                lastName = currentState.lastName.copy(
                    content = newValue,
                    errorMessage = newValue.validateLength()
                )
            )
        }
    }
    fun onAgeNameChange(newValue: String) {
        attendeeUIState.update { currentState ->
            currentState?.copy(
                age = currentState.age.copy(
                    content = newValue,
                    errorMessage = newValue.validateLength()
                )
            )
        }
    }

    fun getAttendeeById(attendeeId: Long) {
        if (attendeeId == -1L) {
            attendeeUIState.update { AttendeeForm() }
            return
        }
        viewModelScope.launch {
            attendeesRepository.getAttendeeById(attendeeId)?.let { attendee ->
                attendeeUIState.update { attendee.toAttendeeForm() }
            }
        }
    }

    fun onSaveAttendee() {
        viewModelScope.launch {
            attendeeUIState.value?.let { attendeeForm ->
                with(attendeeForm) {
                    if (attendeeId != null) {
                        attendeesRepository.updateAttendee(
                            Attendee(
                                id = attendeeId,
                                firstName = firstName.content,
                                lastName = lastName.content,
                                age = age.content.toInt()
                            )
                        )
                    } else {
                        attendeesRepository.addAttendee(
                            Attendee(
                                firstName = firstName.content,
                                lastName = lastName.content,
                                age = age.content.toInt()
                            )
                        )
                    }
                }
            }
        }
    }

}
data class AttendeeForm(
    val attendeeId: Long? = null,
    val firstName: TextFieldState = TextFieldState(),
    val lastName: TextFieldState = TextFieldState(),
    val age: TextFieldState = TextFieldState()
)

fun Attendee.toAttendeeForm(): AttendeeForm =
    AttendeeForm(
        attendeeId = id,
        firstName = TextFieldState(content = firstName),
        lastName = TextFieldState(content = lastName),
        age = TextFieldState(content = age.toString()),
    )

