package pl.marchuck.workmanagerexample.database

import io.reactivex.Flowable

interface AppDatabase {

    fun getPendingProtocols(): Flowable<List<OfflineProtocol>>

    fun getPhotosByProtocolId(id: Long): Flowable<List<OfflineProtocolPhoto>>
}