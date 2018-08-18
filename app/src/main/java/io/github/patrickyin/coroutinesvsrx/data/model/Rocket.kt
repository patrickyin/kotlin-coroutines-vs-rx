package io.github.patrickyin.coroutinesvsrx.data.model

import com.google.gson.annotations.SerializedName

data class Rocket(
        @SerializedName("rocket_id")
        var rocketId: String? = null
)
