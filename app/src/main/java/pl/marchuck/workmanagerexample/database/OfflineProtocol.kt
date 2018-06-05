package pl.marchuck.workmanagerexample.database

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "offline_protocol",primaryKeys = ["id"])
data class OfflineProtocol(@PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") var id: Long?,
                           @ColumnInfo(name = "protocol_id") var protocol_id: Long? = null,
                           @ColumnInfo(name = "user_token") var user_token: String,
                           @ColumnInfo(name = "title") var title: String,
                           @ColumnInfo(name = "description") var description: String,
                           @ColumnInfo(name = "date") var timestamp: Long)
