package io.github.patrickyin.coroutinesvsrx.data

import io.github.patrickyin.coroutinesvsrx.data.model.Launch
import io.github.patrickyin.coroutinesvsrx.data.model.RocketDetail
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface RxRestfulAPI {
    @GET("launches/next")
    fun getNextLaunch(): Single<Launch>

    @GET("rockets/{rocketId}")
    fun getRocket(@Path("rocketId") rocketId: String): Single<RocketDetail>
}