package com.danielwaiguru.gdscmut_room.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Attendee::class], version = 1)
abstract class AttendeesDatabase: RoomDatabase() {
    abstract fun attendeeDao(): AttendeeDao

    companion object {
        /**
         * Singleton prevents multiple instances of database opening at the same time.
         */
        @Volatile
        private var INSTANCE: AttendeesDatabase? = null

        fun getDatabase(context: Context): AttendeesDatabase {
            return INSTANCE ?:  synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AttendeesDatabase::class.java,
                    "attendees"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}