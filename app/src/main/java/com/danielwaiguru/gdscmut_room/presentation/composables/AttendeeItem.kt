package com.danielwaiguru.gdscmut_room.presentation.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.danielwaiguru.gdscmut_room.R
import com.danielwaiguru.gdscmut_room.data.Attendee

@Composable
fun AttendeeItem(
    attendee: Attendee,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
    ) {
        Column(
            modifier = Modifier.padding(12.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = R.drawable.verified_event),
                    contentDescription = null,
                    modifier = Modifier
                        .size(40.dp)
                        .clip(
                            MaterialTheme.shapes.small
                        ),
                    contentScale = ContentScale.Crop
                )
                Spacer(modifier = Modifier.width(8.dp))
                Column(
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                ) {
                    Text(
                        text = attendee.firstName + " " + attendee.lastName,
                        style = MaterialTheme.typography.h5,
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = attendee.age.toString(),
                        style = MaterialTheme.typography.subtitle2,
                        textAlign = TextAlign.End
                    )
                }
            }
        }
    }
}