package pl.marchuck.workmanagerexample.model

import android.arch.persistence.room.Entity
import com.google.gson.annotations.SerializedName

@Entity
data class Protocol (@SerializedName("user_token") val user_token: String,
                     @SerializedName("protocol_data") val protocol_data: ProtocolData)