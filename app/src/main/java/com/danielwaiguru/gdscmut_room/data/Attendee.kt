package com.danielwaiguru.gdscmut_room.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.danielwaiguru.gdscmut_room.data.Attendee.Companion.ATTENDEES_TABLE_NAME
import com.danielwaiguru.gdscmut_room.data.Occupation.Companion.OCCUPATION_TABLE_NAME

@Entity(tableName = ATTENDEES_TABLE_NAME)
data class Attendee(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    @ColumnInfo(name = "first_name")
    val firstName: String,
    @ColumnInfo(name = "last_name")
    val lastName: String,
    val age: Int
) {
    companion object {
        const val ATTENDEES_TABLE_NAME = "attendees"
    }
}
@Entity(
    tableName = OCCUPATION_TABLE_NAME,
    foreignKeys = [
        ForeignKey(
            entity = Attendee::class,
            childColumns = ["attendee_id"],
            parentColumns = ["id"]
        )
    ]
)
data class Occupation(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    @ColumnInfo("attendee_id")
    val attendeeId: Long,
    val title: String,
    val company: String,
    @ColumnInfo(name = "employee_id", defaultValue = "N/A")
    val employeeID: String
) {
    companion object {
        const val OCCUPATION_TABLE_NAME = "attendees_occupation"
    }
}
