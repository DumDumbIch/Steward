package ru.dumdumbich.android.steward.domain.base.event

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.core.parameter.parametersOf
import ru.dumdumbich.android.steward.domain.base.event.type.EventType
import ru.dumdumbich.android.steward.tools.logger.Logger


abstract class BaseEventBus<ET : EventType> : EventBus<ET>, KoinComponent {

    protected val logger: Logger by inject { parametersOf(this, true) }

    protected val eventBusScope: CoroutineScope =
        CoroutineScope(Dispatchers.Default + SupervisorJob())
    private var _event: MutableStateFlow<ET>? = null
    val event: MutableStateFlow<ET> get() = _event!!

    private var _eventTypeEmpty: ET? = null
    private val eventTypeEmpty: ET get() = _eventTypeEmpty!!


    override fun init(emptyEventType: ET) {
        _eventTypeEmpty = emptyEventType
        _event = MutableStateFlow(eventTypeEmpty)
    }

    override fun clearEvent(emptyEventType: ET) {
        eventBusScope.launch {
            event.update { emptyEventType }
        }
    }

    override fun eventsHandler(eventsHandler: (ET) -> Unit) {
        event.onEach { event ->
            eventsHandler(event)
            clearEvent(eventTypeEmpty)
        }.launchIn(eventBusScope)
    }
}
