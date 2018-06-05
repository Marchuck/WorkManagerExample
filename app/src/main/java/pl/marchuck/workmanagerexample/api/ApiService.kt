package pl.marchuck.workmanagerexample.api

import io.reactivex.Single
import pl.marchuck.workmanagerexample.model.Protocol
import pl.marchuck.workmanagerexample.model.ProtocolPhoto
import pl.marchuck.workmanagerexample.model.ProtocolPhotoResponse
import pl.marchuck.workmanagerexample.model.ProtocolResponse

interface ApiService {

    fun postProtocol(protocol: Protocol): Single<ProtocolResponse>

    fun postPhoto(protocolPhoto: ProtocolPhoto): Single<ProtocolPhotoResponse>
}