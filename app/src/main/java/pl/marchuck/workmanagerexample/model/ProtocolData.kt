package pl.marchuck.workmanagerexample.model

import com.google.gson.annotations.SerializedName

data class ProtocolData(@SerializedName("title") val title: String,
                        @SerializedName("description") val description: String,
                        @SerializedName("timestamp") val timestamp: Long)