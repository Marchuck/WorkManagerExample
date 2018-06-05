package pl.marchuck.workmanagerexample.simple

import androidx.work.Worker
import pl.marchuck.workmanagerexample.api.ApiService
import pl.marchuck.workmanagerexample.database.RoomDb
import pl.marchuck.workmanagerexample.model.ProtocolMapper
import javax.inject.Inject

class SimpleWorkManagerExample @Inject constructor(
        val mapper: ProtocolMapper,
        val apiService: ApiService,
        val database: RoomDb) {


    companion object {
        val PROTOCOL_MODEL = "PROTOCOL_MODEL"
        val PROTOCOL_ID = "PROTOCOL_ID"
        val OFFLINE_ID = "OFFLINE_ID"
    }

    fun execute() {


    }

    inner class PostProtocolWork : Worker() {

        override fun doWork(): WorkerResult {

            val model = inputData.getString(PROTOCOL_MODEL, null)
            val offline = mapper.fromStringToOffline(model) ?: return WorkerResult.FAILURE
            val request = mapper.fromOfflineToProtocol(offline)
            val response = apiService.postProtocol(request).blockingGet()


            val online_protocol_id = response.protocol_id
            if (online_protocol_id != null) {

                //protocol successfully posted. now update all photos associated with this photo

                database.protocolDao().update(
                        offline.apply { protocol_id = online_protocol_id }
                )

                outputData.keyValueMap.apply {
                    put(PROTOCOL_ID, response.protocol_id)
                    put(OFFLINE_ID, offline.id)
                }
                return WorkerResult.SUCCESS
            }
            return WorkerResult.FAILURE
        }

    }

    inner class PostProtocolPhotoWork : Worker() {

        override fun doWork(): WorkerResult {

            val protcolId = inputData.getLong(PROTOCOL_ID, -1)


            return WorkerResult.SUCCESS

        }
    }
}
