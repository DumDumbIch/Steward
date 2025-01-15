package ru.dumdumbich.android.steward.server.iot

import android.content.Intent
import android.widget.Toast
import ru.dumdumbich.android.steward.server.base.BaseService

class IotService : BaseService() {

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Toast.makeText(this, "IoT Service starting", Toast.LENGTH_SHORT).show()
        startMode = START_STICKY
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onDestroy() {
        super.onDestroy()
        Toast.makeText(this, "IoT Service done", Toast.LENGTH_SHORT).show()
    }
}
