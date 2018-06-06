package pl.marchuck.workmanagerexample

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import pl.marchuck.workmanagerexample.database.OfflineProtocol
import pl.marchuck.workmanagerexample.database.RoomDb
import pl.marchuck.workmanagerexample.simple.SimpleWorkManagerExample
import java.util.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        findViewById<Button>(R.id.add_protocol).setOnClickListener {

            val protocolDao = RoomDb.getInstance(MainActivity@ this).protocolDao()
            protocolDao.apply {
                add(OfflineProtocol(id = 0, description = UUID.randomUUID().toString(), timestamp = 1L, title = "none"))
                add(OfflineProtocol(id = 1, description = UUID.randomUUID().toString(), timestamp = 1L, title = "none"))
                add(OfflineProtocol(id = 2, description = UUID.randomUUID().toString(), timestamp = 1L, title = "none"))
                add(OfflineProtocol(id = 3, description = UUID.randomUUID().toString(), timestamp = 1L, title = "none"))

            }

        }
        findViewById<Button>(R.id.perform_sync).setOnClickListener {
            SimpleWorkManagerExample.getInstance(MainActivity@ this).execute()
        }
    }
}
