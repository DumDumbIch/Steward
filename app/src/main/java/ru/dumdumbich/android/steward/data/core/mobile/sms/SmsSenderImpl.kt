package ru.dumdumbich.android.steward.data.core.mobile.sms

import android.telephony.SmsManager


class SmsSenderImpl : SmsSender {

    private val subscriptionId = SmsManager.getDefaultSmsSubscriptionId()
    private val smsManager = SmsManager.getSmsManagerForSubscriptionId(subscriptionId)

    override fun send(message: String) {
        smsManager.sendTextMessage("+79158212071", null, message, null, null)
    }
}
