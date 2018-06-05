package pl.marchuck.workmanagerexample.database

import android.arch.persistence.room.*
import io.reactivex.Flowable

@Dao
interface ProtocolDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun add(protocol: OfflineProtocol): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun add(protocol: OfflineProtocolPhoto): Long

    @Update
    fun update(protocol: OfflineProtocol)

    @Delete
    fun delete(protocol: OfflineProtocol)

    @Delete
    fun delete(photo: OfflineProtocolPhoto)

    @Query("SELECT * FROM offline_protocol WHERE protocol_id = null")
    fun getPendingPhotos(): Flowable<List<OfflineProtocol>>

    @Query("SELECT * FROM offline_protocol_photo WHERE offline_protocol_id = :id")
    fun getPhotosByProtocolId(id: Long): Flowable<List<OfflineProtocolPhoto>>


}