package com.danielwaiguru.gdscmut_room.utils

fun String.validateLength(): String? = if (this.isEmpty()) {
    "Field cannot be empty"
} else null