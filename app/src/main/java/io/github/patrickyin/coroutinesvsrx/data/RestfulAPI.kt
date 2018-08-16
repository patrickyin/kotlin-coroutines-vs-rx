package io.github.patrickyin.coroutinesvsrx.data

import io.reactivex.Single
import retrofit2.http.GET

interface RestfulAPI {
    @GET("launches/latest")
    fun getNextLaunch(): Single<Launch>
}