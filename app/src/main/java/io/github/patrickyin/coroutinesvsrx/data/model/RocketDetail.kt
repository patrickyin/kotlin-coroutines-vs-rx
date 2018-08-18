package io.github.patrickyin.coroutinesvsrx.data.model

import com.google.gson.annotations.SerializedName

data class RocketDetail(
        @SerializedName("name")
        var name: String? = null,
        @SerializedName("stages")
        var stages: String? = null,
        @SerializedName("cost_per_launch")
        var costPerLaunch: String? = null
)
