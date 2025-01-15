package ru.dumdumbich.android.steward.domain.core.event

import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ru.dumdumbich.android.steward.domain.base.event.BaseEventBus
import ru.dumdumbich.android.steward.domain.base.event.type.Sms


class SmsEventBus : BaseEventBus<Sms>() {

    init {
        init(Sms.Empty)
    }

    fun onTextSmsReceived(message: String) {
        eventBusScope.launch {
            event.update { Sms.TextSms(message) }
        }
    }

    fun onDataSmsReceived(data: Array<Byte>) {
        eventBusScope.launch {
            event.update { Sms.DataSms(data) }
        }
    }
}
