package pl.marchuck.workmanagerexample.model

import com.google.gson.annotations.SerializedName

class ProtocolPhotoResponse(@SerializedName("message") val message: String?,
                            @SerializedName("error") val error: String? = null,
                            @SerializedName("error_code") val error_code: Int = 0)