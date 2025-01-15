# SMS

Добавить в манифест

```xml
    <uses-feature
        android:name="android.hardware.telephony"
        android:required="true" />
    <uses-permission android:name="android.permission.SEND_SMS" />
    <uses-permission android:name="android.permission.READ_SMS" />
    <uses-permission android:name="android.permission.RECEIVE_SMS" />
```

Добавить в метод onViewCreated() фрагмента, в главный фрагмент или в тот, в котором будут использоваться СМС

```kotlin
        if (activity?.checkSelfPermission(android.Manifest.permission.RECEIVE_SMS) != PackageManager.PERMISSION_GRANTED
            || activity?.checkSelfPermission(android.Manifest.permission.READ_SMS) != PackageManager.PERMISSION_GRANTED
            || activity?.checkSelfPermission(android.Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED
        ) {
            activity?.requestPermissions(
                arrayOf(android.Manifest.permission.RECEIVE_SMS,
                    android.Manifest.permission.SEND_SMS,
                    android.Manifest.permission.READ_SMS), PackageManager.PERMISSION_GRANTED
            )
        }
```

Для приложений SingleActivity лучше добавить эту проверку в метод onCreate() в активити

```kotlin
        if (checkSelfPermission(android.Manifest.permission.RECEIVE_SMS) != PackageManager.PERMISSION_GRANTED
            || checkSelfPermission(android.Manifest.permission.READ_SMS) != PackageManager.PERMISSION_GRANTED
            || checkSelfPermission(android.Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED
        ) {
            requestPermissions(
                arrayOf(android.Manifest.permission.RECEIVE_SMS,
                    android.Manifest.permission.SEND_SMS,
                    android.Manifest.permission.READ_SMS), PackageManager.PERMISSION_GRANTED
            )
        }
```


Класс-провайдер для отправки СМС (пример)

```kotlin
class SmsProviderImpl : SmsProvider {

    private val subscriptionId = SmsManager.getDefaultSmsSubscriptionId()
    private val smsManager = SmsManager.getSmsManagerForSubscriptionId(subscriptionId)


    override fun send(message: String) {
        smsManager.sendTextMessage("+79158212071", null, message, null, null)
    }
}
```


Получение СМС

```kotlin

```