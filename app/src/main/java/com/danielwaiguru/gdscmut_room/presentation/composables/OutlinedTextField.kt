package com.danielwaiguru.gdscmut_room.presentation.composables

import androidx.annotation.StringRes
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import com.danielwaiguru.gdscmut_room.utils.TextFieldState

@Composable
fun OutlinedTextField(
    state: TextFieldState,
    onNewValue: (String) -> Unit,
    @StringRes placeholderId: Int,
    modifier: Modifier = Modifier
) {
    return OutlinedTextField(
        value = state.content,
        onValueChange = { onNewValue(it) },
        placeholder = {
            Text(text = stringResource(placeholderId))
        },
        shape = MaterialTheme.shapes.medium,
        modifier = modifier,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Email,
            imeAction = ImeAction.Next
        ),
        isError = state.errorMessage != null,
        singleLine = true,
    )
}