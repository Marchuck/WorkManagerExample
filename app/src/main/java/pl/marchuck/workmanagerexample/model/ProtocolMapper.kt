package pl.marchuck.workmanagerexample.model

import com.google.gson.Gson
import pl.marchuck.workmanagerexample.database.OfflineProtocol
import javax.inject.Inject

class ProtocolMapper @Inject constructor(val gson: Gson) {

    fun fromOfflineToProtocol(offlineProtocol: OfflineProtocol) = Protocol(
            user_token = offlineProtocol.user_token,
            protocol_data = ProtocolData(
                    title = offlineProtocol.title,
                    description = offlineProtocol.description,
                    timestamp = offlineProtocol.timestamp
            )
    )

    fun fromStringToOffline(value: String) = gson.fromJson(value, OfflineProtocol::class.java)


}