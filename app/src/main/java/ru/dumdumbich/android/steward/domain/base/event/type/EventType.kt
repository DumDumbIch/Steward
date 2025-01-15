package ru.dumdumbich.android.steward.domain.base.event.type

sealed class EventType

data object Command: EventType()
data object Error: EventType()
