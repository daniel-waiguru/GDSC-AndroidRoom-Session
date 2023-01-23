package com.danielwaiguru.gdscmut_room.presentation.attendees_listing

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.danielwaiguru.gdscmut_room.data.Attendee
import com.danielwaiguru.gdscmut_room.navigation.ADD_ATTENDEE_SCREEN
import com.danielwaiguru.gdscmut_room.presentation.composables.AttendeeItem
import com.danielwaiguru.gdscmut_room.presentation.composables.EmptyState
import com.danielwaiguru.gdscmut_room.presentation.composables.TopBar

@Composable
fun AttendeesRoute(
    modifier: Modifier = Modifier,
    viewModel: AttendeesViewModel,
    onClick: (String, Long) -> Unit,
) {
    val allAttendees by viewModel.allAttendees.collectAsStateWithLifecycle()
    AttendeesScreen(
        attendees = allAttendees,
        onClick = onClick,
        modifier = modifier
    )
}

@Composable
fun AttendeesScreen(
    attendees: List<Attendee>,
    onClick: (String, Long) -> Unit,
    modifier: Modifier = Modifier,
) {
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = {
                onClick(ADD_ATTENDEE_SCREEN, -1L)
            }) {
                Icon(imageVector = Icons.Rounded.Add, contentDescription = null)
            }
        },
        topBar = {
            TopBar(title = "GDSC Event Attendees")
        },
        modifier = modifier
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)

        ) {
            if (attendees.isNotEmpty()) {
                AttendeesList(
                    modifier = Modifier,
                    attendees = attendees,
                    onClick = onClick
                )
            }
            val noAttendeesAdded = attendees.isEmpty()
            AnimatedVisibility(visible = noAttendeesAdded) {
                EmptyState(modifier = Modifier)
            }
        }
    }
}

@Composable
fun AttendeesList(
    modifier: Modifier = Modifier,
    attendees: List<Attendee>,
    onClick: (String, Long) -> Unit,
) {
    LazyColumn(modifier = modifier) {
        items(attendees, key = { it.id }) { attendee ->
            AttendeeItem(
                attendee = attendee,
                modifier = Modifier
                    .clickable { onClick(ADD_ATTENDEE_SCREEN, attendee.id) }
                    .padding(5.dp)
                    .fillMaxWidth()
            )
        }
    }
}
