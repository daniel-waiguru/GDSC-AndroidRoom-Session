package com.danielwaiguru.gdscmut_room

import android.app.Application
import com.danielwaiguru.gdscmut_room.data.AttendeesDatabase
import com.danielwaiguru.gdscmut_room.data.AttendeesRepository

class GDSCMutApp: Application() {
    private val attendeesDatabase by lazy { AttendeesDatabase.getDatabase(this) }
    val attendeesRepository by lazy { AttendeesRepository(attendeesDatabase.attendeeDao()) }
}