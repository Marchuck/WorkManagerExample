package pl.marchuck.workmanagerexample.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context

@Database(entities = [(OfflineProtocol::class), (OfflineProtocolPhoto::class)], version = 1, exportSchema = false)
abstract class RoomDb : RoomDatabase() {

    abstract fun protocolDao(): ProtocolDao

    companion object {
        private var instance: RoomDb? = null

        @Synchronized
        fun getInstance(context: Context): RoomDb {
            if (instance == null) {
                instance = Room
                        .databaseBuilder(context.applicationContext, RoomDb::class.java, "offline_database")
                        .build()
            }
            return instance!!
        }
    }

}