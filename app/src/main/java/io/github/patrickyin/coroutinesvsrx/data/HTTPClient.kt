package io.github.patrickyin.coroutinesvsrx.data

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.experimental.CoroutineCallAdapterFactory
import io.github.patrickyin.coroutinesvsrx.BuildConfig
import retrofit2.CallAdapter
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object HTTPClient {
    val rxRestfulAPI: RxRestfulAPI by lazy {
        initializeRetrofit<RxRestfulAPI>(RxJava2CallAdapterFactory.create())
    }

    val coroutineRestfulAPI: CoroutineRestfulAPI by lazy {
        initializeRetrofit<CoroutineRestfulAPI>(CoroutineCallAdapterFactory())
    }

    private inline fun <reified T> initializeRetrofit(retrofitFactory: CallAdapter.Factory): T {
        val retrofit = Retrofit.Builder()
                .baseUrl(BuildConfig.ENDPOINT)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(retrofitFactory)
                .build()

        return retrofit.create(T::class.java)
    }
}