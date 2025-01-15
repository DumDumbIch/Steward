package ru.dumdumbich.android.steward.data.core.mobile.sms

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.core.parameter.parametersOf
import ru.dumdumbich.android.steward.domain.core.event.SmsEventBus
import ru.dumdumbich.android.steward.tools.logger.Logger


class SmsReceiver : BroadcastReceiver(), KoinComponent {

    private val logger: Logger by inject { parametersOf(this, true) }
    private val smsEventBus: SmsEventBus by inject()

    override fun onReceive(context: Context?, intent: Intent?) {
        if (intent?.action == "android.provider.Telephony.SMS_RECEIVED") {
            // it's best practice to verify intent action before performing any operation
            logger.toConsole("SMS Received")
            smsEventBus.onTextSmsReceived("Sample message from SmsReceiver")
        }
    }
}
