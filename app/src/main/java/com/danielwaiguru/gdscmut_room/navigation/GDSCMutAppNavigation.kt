package com.danielwaiguru.gdscmut_room.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.danielwaiguru.gdscmut_room.presentation.add_attendee.AddEditAttendeeRoute
import com.danielwaiguru.gdscmut_room.presentation.attendees_listing.AttendeesRoute
import com.danielwaiguru.gdscmut_room.presentation.attendees_listing.AttendeesViewModel


@Composable
fun GDSCMutAppNavigation(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    viewModel: AttendeesViewModel,
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = ATTENDEES_SCREEN
    ) {
        gdscMutGraph(
            navController = navController,
            viewModel = viewModel
        )
    }
}

fun NavGraphBuilder.gdscMutGraph(
    navController: NavHostController,
    viewModel: AttendeesViewModel
) {
    composable(ATTENDEES_SCREEN) {
        AttendeesRoute(
            viewModel = viewModel
        ){ route, attendeeId ->
            val qualifiedRoute = "${route.replace("{attendeeId}", "")}${attendeeId}"
            navController.navigate(qualifiedRoute)
        }
    }
    composable(
        ADD_ATTENDEE_SCREEN,
        arguments = listOf(navArgument("attendeeId") { type = NavType.LongType })
    ) { backStackEntry ->
        val attendeeId = backStackEntry.arguments?.getLong("attendeeId") ?: -1L
        AddEditAttendeeRoute(viewModel = viewModel, attendeeId = attendeeId) {
            navController.popBackStack()
        }
    }
}

const val ATTENDEES_SCREEN = "AttendeesScreen"
const val ADD_ATTENDEE_SCREEN = "AddAttendeeScreen/{attendeeId}"