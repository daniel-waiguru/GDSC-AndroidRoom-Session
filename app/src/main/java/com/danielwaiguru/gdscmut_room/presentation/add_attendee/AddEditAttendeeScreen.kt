package com.danielwaiguru.gdscmut_room.presentation.add_attendee

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.danielwaiguru.gdscmut_room.R
import com.danielwaiguru.gdscmut_room.presentation.attendees_listing.AttendeeForm
import com.danielwaiguru.gdscmut_room.presentation.attendees_listing.AttendeesViewModel
import com.danielwaiguru.gdscmut_room.presentation.composables.OutlinedTextField
import com.danielwaiguru.gdscmut_room.presentation.composables.RoundedButton
import com.danielwaiguru.gdscmut_room.presentation.composables.TopBar



@Composable
fun AddEditAttendeeRoute(
    modifier: Modifier = Modifier,
    viewModel: AttendeesViewModel,
    attendeeId: Long,
    onNavBack: () -> Unit
) {
    LaunchedEffect(attendeeId) {
        viewModel.getAttendeeById(attendeeId = attendeeId)
    }
    val attendeeFormState by viewModel.attendeeFormUIState.collectAsStateWithLifecycle()
    AddEditAttendeeScreen(
        state = attendeeFormState,
        onFirstNameChange = viewModel::onFirstNameChange,
        onLastNameChange = viewModel::onLastNameChange,
        onAgeChange = viewModel::onAgeNameChange,
        onSaveAttendee = {
            viewModel.onSaveAttendee()
            onNavBack()
        },
        modifier = modifier
            .fillMaxSize()
    )
}

@Composable
fun AddEditAttendeeScreen(
    state: AttendeeForm,
    onFirstNameChange: (String) -> Unit,
    onLastNameChange: (String) -> Unit,
    onAgeChange: (String) -> Unit,
    onSaveAttendee: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
    ) {
        TopBar(
            title = "Add Attendee",
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            state = state.firstName,
            onNewValue = onFirstNameChange,
            placeholderId = R.string.first_name,
            modifier = Modifier
                .padding(12.dp)
                .fillMaxWidth()
        )
        OutlinedTextField(
            state = state.lastName,
            onNewValue = onLastNameChange,
            placeholderId = R.string.last_name,
            modifier = Modifier
                .padding(12.dp)
                .fillMaxWidth()
        )
        OutlinedTextField(
            state = state.age,
            onNewValue =  onAgeChange ,
            placeholderId = R.string.age,
            modifier = Modifier
                .padding(12.dp)
                .fillMaxWidth()
        )
        RoundedButton(
            textId = R.string.save_attendee,
            modifier = Modifier
                .padding(12.dp)
                .heightIn(min = 50.dp)
                .fillMaxWidth()
            ,
            onClick = onSaveAttendee
        )
    }
}

