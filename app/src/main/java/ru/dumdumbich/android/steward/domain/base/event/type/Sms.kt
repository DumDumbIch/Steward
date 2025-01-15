package ru.dumdumbich.android.steward.domain.base.event.type


sealed class Sms: EventType() {

    data object Empty: Sms()

    data class TextSms(val text: String): Sms()

    data class DataSms(val data: Array<Byte>): Sms() {
        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (javaClass != other?.javaClass) return false

            other as DataSms

            return data.contentEquals(other.data)
        }

        override fun hashCode(): Int {
            return data.contentHashCode()
        }

    }
}
