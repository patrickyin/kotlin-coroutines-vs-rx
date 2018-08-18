package io.github.patrickyin.coroutinesvsrx.data.model

import com.google.gson.annotations.SerializedName

data class Launch(
        @SerializedName("rocket")
        var rocket: Rocket? = null,
        @SerializedName("launch_site")
        var launchSite: LaunchSite? = null
)
