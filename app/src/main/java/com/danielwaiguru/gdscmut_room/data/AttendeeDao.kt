package com.danielwaiguru.gdscmut_room.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface AttendeeDao {
    //Transactions
    @Query("SELECT * FROM attendees")
    fun getAttendees(): Flow<List<Attendee>>

    @Query("SELECT * FROM attendees WHERE id = :attendeeId")
    suspend fun getAttendeeById(attendeeId: Long): Attendee?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addAttendee(attendee: Attendee)

    @Update
    suspend fun updateAttendee(attendee: Attendee)

    @Delete
    suspend fun deleteAttendee(attendee: Attendee)
}