package pl.marchuck.workmanagerexample.model

import com.google.gson.annotations.SerializedName

class ProtocolResponse(@SerializedName("message") val message: String?,
                       @SerializedName("protocol_id") val protocol_id: Long?,
                       @SerializedName("error") val error: String? = null,
                       @SerializedName("error_code") val error_code: Int = 0)