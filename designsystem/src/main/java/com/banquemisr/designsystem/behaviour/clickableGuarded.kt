package com.banquemisr.designsystem.behaviour

import androidx.compose.foundation.*
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.runtime.remember
import androidx.compose.ui.*
import androidx.compose.ui.semantics.Role

fun Modifier.clickableGuarded(
    debounceTime: Long = 1000L,
    enabled: Boolean = true,
    onClickLabel: String? = null,
    role: Role? = null,
    showRipple: Boolean = true,
    onClick: () -> Unit
) = composed {
    val guardedTimedEvent = remember { GuardedTimedEvent() }
    val indication = if (showRipple) LocalIndication.current else null
    this.clickable(
        enabled = enabled,
        onClickLabel = onClickLabel,
        onClick = { guardedTimedEvent.invoke(debounceTime) { onClick() } },
        role = role,
        indication = indication,
        interactionSource = remember { MutableInteractionSource() }
    )
}
