package pl.marchuck.workmanagerexample.simple

import android.content.Context
import androidx.work.*
import com.google.gson.Gson
import pl.marchuck.workmanagerexample.api.ApiService
import pl.marchuck.workmanagerexample.api.RetrofitRestServiceImpl
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

        fun getInstance(context: Context): SimpleWorkManagerExample {

            val gson = Gson()
            val db = RoomDb.getInstance(context)
            val apiService = RetrofitRestServiceImpl("https//:rand-ompa.ge/", gson, 5)
            return SimpleWorkManagerExample(ProtocolMapper(gson), apiService, db)
        }
    }

    fun execute() {
        val disposable = database.protocolDao()
                .getPendingPhotos()
                .flatMapIterable { x -> x }
                .map { value ->
                    OneTimeWorkRequestBuilder<PostProtocolWork>()
                            .setInputData(Data.Builder()
                                    .putString(PROTOCOL_MODEL, mapper.offlineToString(value))
                                    .build())
                            .setConstraints(provideConstrains())
                            .build()
                }
                .scan(initialWork(), { currentChainOfWorkRequests, nextWorkRequest ->
                    currentChainOfWorkRequests.then(nextWorkRequest)
                })
                .subscribe({ completedChain ->

                    completedChain.enqueue()

                }, { error ->
                    println(error)
                })
    }

    private fun initialWork() = workerManagerInstance().beginWith(successWork())

    private fun provideConstrains() = Constraints.Builder()
            .setRequiresCharging(true)
            .build()


    private fun successWork(): OneTimeWorkRequest = OneTimeWorkRequestBuilder<InitialWork>().build()

    class InitialWork : Worker() {
        override fun doWork() = WorkerResult.SUCCESS
    }

    private fun workerManagerInstance() = WorkManager.getInstance()

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
