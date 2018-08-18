package io.github.patrickyin.coroutinesvsrx.data

import io.github.patrickyin.coroutinesvsrx.data.model.Launch
import io.github.patrickyin.coroutinesvsrx.data.model.RocketDetail
import kotlinx.coroutines.experimental.Deferred
import retrofit2.http.GET
import retrofit2.http.Path

interface CoroutineRestfulAPI {
    @GET("launches/next")
    fun getNextLaunch(): Deferred<Launch>

    @GET("rockets/{rocketId}")
    fun getRocket(@Path("rocketId") rocketId: String): Deferred<RocketDetail>
}