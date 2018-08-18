package io.github.patrickyin.coroutinesvsrx

import android.os.Bundle
import android.support.annotation.UiThread
import android.support.v7.app.AppCompatActivity
import io.github.patrickyin.coroutinesvsrx.data.HTTPClient
import io.github.patrickyin.coroutinesvsrx.data.model.RocketDetail
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.cancel
import kotlinx.coroutines.experimental.launch


class MainActivity : AppCompatActivity() {

    private val disposables = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        clear.setOnClickListener { text_view.text = null }

        load_rx.setOnClickListener { fetchDataRx() }

        load_coroutines.setOnClickListener { fetchDataCoroutines() }
    }

    override fun onDestroy() {
        super.onDestroy()

        disposables.clear()
        CommonPool.cancel()
    }

    private fun fetchDataCoroutines() {
        val restfulAPI = HTTPClient.coroutineRestfulAPI
        launch(UI) {
            try {
                val nextLaunch = restfulAPI.getNextLaunch().await()
                val rocketDetail = restfulAPI.getRocket(nextLaunch.rocket?.rocketId ?: "").await()
                val result = formatString(rocketDetail)
                updateUI(result)
            } catch (e: Throwable) {
                showError(e)
            }
        }
    }

    private fun fetchDataRx() {
        val restfulAPI = HTTPClient.rxRestfulAPI

        val disposable = restfulAPI.getNextLaunch()
                .flatMap { restfulAPI.getRocket(it.rocket?.rocketId ?: "0") }
                .map { formatString(it) }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ text -> updateUI(text) }, { showError(it) })
        disposables.add(disposable)
    }

    private fun formatString(rocketDetail: RocketDetail) =
            "Rocket name: ${rocketDetail.name}\nStages: ${rocketDetail.stages} \nCost per launch: ${rocketDetail.costPerLaunch}"

    private fun showError(throwable: Throwable) {
        text_view.text = throwable.localizedMessage
    }

    @UiThread
    private fun updateUI(text: String) {
        text_view.text = text
    }
}
