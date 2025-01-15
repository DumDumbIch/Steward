package ru.dumdumbich.android.steward.domain.usecase.sms

import ru.dumdumbich.android.steward.domain.base.event.type.Sms
import ru.dumdumbich.android.steward.domain.core.event.SmsEventBus


class ReceivedSmsEventsUseCase(private val eventBus: SmsEventBus) {

    operator fun invoke(
        performIfEmptyMessageWasReceived: () -> Unit,
        performIfTextSmsMessageWasReceived: (String) -> Unit,
        performIfDataSmsMessageWasReceived: (Array<Byte>) -> Unit,
    ) {
        eventBus.eventsHandler { event ->
            when (event) {
                Sms.Empty -> performIfEmptyMessageWasReceived()
                is Sms.TextSms -> performIfTextSmsMessageWasReceived(event.text)
                is Sms.DataSms -> performIfDataSmsMessageWasReceived(event.data)
            }
        }
    }
}
