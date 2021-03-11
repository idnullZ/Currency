package idnull.znz.currency.domain.pojocoin.dataUsd

import androidx.room.Entity
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName



data class Respond(
        @SerializedName("success")
        @Expose
        val success: Boolean? = null,
        @SerializedName("timestamp")
        @Expose
        val timestamp: Int? = null,
        @SerializedName("base")
        @Expose
        val base: String? = null,
        @SerializedName("date")
        @Expose
        val date: String? = null,
        @SerializedName("rates")
        @Expose
        val rates: Rates? = null,
)
