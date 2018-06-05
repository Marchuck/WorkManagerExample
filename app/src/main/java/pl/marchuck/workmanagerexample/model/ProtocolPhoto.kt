package pl.marchuck.workmanagerexample.model

import com.google.gson.annotations.SerializedName

data class ProtocolPhoto(@SerializedName("user_token") val user_token: String,
                         @SerializedName("protocol_id") val protocol_id: String,
                         @SerializedName("photo_base64") val photo_base64: String,
                         @SerializedName("filename") val filename: String)