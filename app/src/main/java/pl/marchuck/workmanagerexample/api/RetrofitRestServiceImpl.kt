package pl.marchuck.workmanagerexample.api

import com.google.gson.Gson
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import io.reactivex.Single
import okhttp3.OkHttpClient
import okhttp3.Protocol
import okhttp3.logging.HttpLoggingInterceptor
import pl.marchuck.workmanagerexample.model.ProtocolPhoto
import pl.marchuck.workmanagerexample.model.ProtocolPhotoResponse
import pl.marchuck.workmanagerexample.model.ProtocolResponse
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RetrofitRestServiceImpl(endpoint: String,
                              gson: Gson,
                              timeoutInSeconds: Int) : RetrofitRestService {

    private val api: RetrofitRestService

    init {

        val gsonConverter = GsonConverterFactory.create(gson)

        api = Retrofit.Builder()
                .baseUrl(endpoint)
                .addConverterFactory(gsonConverter)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(buildClient(timeoutInSeconds.toLong()))
                .build()
                .create(RetrofitRestService::class.java)
    }

    private fun buildClient(timeout: Long): OkHttpClient {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        val builder = OkHttpClient.Builder()
                .readTimeout(timeout, TimeUnit.SECONDS)
                .connectTimeout(timeout, TimeUnit.SECONDS)

        builder.addNetworkInterceptor(loggingInterceptor)

        return builder.build()
    }

    override fun postProtocol(protocol: Protocol): Single<ProtocolResponse> {
        return api.postProtocol(protocol)
    }

    override fun postPhoto(protocolPhoto: ProtocolPhoto): Single<ProtocolPhotoResponse> {
        return api.postPhoto(protocolPhoto)
    }
}