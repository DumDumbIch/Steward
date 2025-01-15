package ru.dumdumbich.android.steward.domain.base.event

import ru.dumdumbich.android.steward.domain.base.event.type.EventType


interface EventBus<ET : EventType> {
    fun init(emptyEventType: ET)
    fun clearEvent(emptyEventType: ET)
    fun eventsHandler(eventsHandler: (ET) -> Unit)
}
