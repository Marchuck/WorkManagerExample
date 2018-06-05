package pl.marchuck.workmanagerexample.database

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.ForeignKey
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "offline_protocol_photo", foreignKeys = [ForeignKey(entity = OfflineProtocol::class, parentColumns = ["id"], childColumns = ["offline_protocol_id"])])
data class OfflineProtocolPhoto(@PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") val id: Long,
                                @ColumnInfo(name = "offline_protocol_id") val protocol_id: Long,
                                @ColumnInfo(name = "user_token") val user_token: String,
                                @ColumnInfo(name = "photo_base64") val photo_base64: String,
                                @ColumnInfo(name = "filename") val filename: String)