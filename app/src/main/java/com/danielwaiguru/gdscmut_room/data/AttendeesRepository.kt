package com.danielwaiguru.gdscmut_room.data

import kotlinx.coroutines.flow.Flow

class AttendeesRepository(private val attendeeDao: AttendeeDao) {
    fun getAttendees(): Flow<List<Attendee>> = attendeeDao.getAttendees()

    suspend fun getAttendeeById(attendeeId: Long): Attendee? =
        attendeeDao.getAttendeeById(attendeeId)

    suspend fun addAttendee(attendee: Attendee) =
        attendeeDao.addAttendee(attendee)

    suspend fun updateAttendee(attendee: Attendee) =
        attendeeDao.updateAttendee(attendee)

    suspend fun deleteAttendee(attendee: Attendee) =
        attendeeDao.deleteAttendee(attendee)
}