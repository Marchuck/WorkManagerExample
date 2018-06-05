package pl.marchuck.workmanagerexample.api

import io.reactivex.Single
import okhttp3.Protocol
import pl.marchuck.workmanagerexample.model.ProtocolPhoto
import pl.marchuck.workmanagerexample.model.ProtocolPhotoResponse
import pl.marchuck.workmanagerexample.model.ProtocolResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface RetrofitRestService {

    @POST("/api/v1/protocol")
    fun postProtocol(@Body protocol: Protocol): Single<ProtocolResponse>

    @POST("/api/v1/protocol/photo")
    fun postPhoto(@Body protocolPhoto: ProtocolPhoto): Single<ProtocolPhotoResponse>
}