package io.github.patrickyin.coroutinesvsrx.data.model

import com.google.gson.annotations.SerializedName

data class LaunchSite(
        @SerializedName("site_id")
        var siteId: String? = null,
        @SerializedName("site_name")
        var siteName: String? = null,
        @SerializedName("site_name_long")
        var siteNameLong: String? = null
)
