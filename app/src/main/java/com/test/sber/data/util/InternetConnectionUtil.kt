package com.test.sber.data.util

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import androidx.annotation.RequiresApi
import io.reactivex.Single


object InternetConnectionUtil {
    fun isInternetOn(context: Context): Single<Boolean> {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            checkConnectionVersionUp23(connectivityManager)
        } else {
            checkConnectionVersionDown23(connectivityManager)
        }
    }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun checkConnectionVersionUp23(connectivityManager: ConnectivityManager): Single<Boolean> {
        val networkCapabilities = connectivityManager.activeNetwork ?: return Single.just(false)

        val actNw =
            connectivityManager.getNetworkCapabilities(networkCapabilities) ?: return Single.just(
                false
            )
        return Single.just(
            when {
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
                else -> false
            }
        )
    }

    private fun checkConnectionVersionDown23(connectivityManager: ConnectivityManager): Single<Boolean> {
        connectivityManager.run {
            connectivityManager.activeNetworkInfo?.run {
                return Single.just(
                    when (type) {
                        ConnectivityManager.TYPE_WIFI -> true
                        ConnectivityManager.TYPE_MOBILE -> true
                        ConnectivityManager.TYPE_ETHERNET -> true
                        else -> false
                    }
                )
            }
        }
        return Single.just(false)
    }
}
