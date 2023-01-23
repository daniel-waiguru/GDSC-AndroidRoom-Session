package com.danielwaiguru.gdscmut_room.presentation.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.danielwaiguru.gdscmut_room.GDSCMutApp
import com.danielwaiguru.gdscmut_room.navigation.GDSCMutAppNavigation
import com.danielwaiguru.gdscmut_room.presentation.attendees_listing.AttendeesViewModel
import com.danielwaiguru.gdscmut_room.presentation.attendees_listing.AttendeesViewModelFactory
import com.danielwaiguru.gdscmut_room.presentation.theme.GDSCMutRoomTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GDSCMutRoomTheme {
                val viewModel: AttendeesViewModel by viewModels {
                    AttendeesViewModelFactory((application as GDSCMutApp).attendeesRepository)
                }
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background,
                ) {
                    GDSCMutAppNavigation(
                        modifier = Modifier,
                        navController = rememberNavController(),
                        viewModel = viewModel,
                    )
                }
            }
        }
    }
}
