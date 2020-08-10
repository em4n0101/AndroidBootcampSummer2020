package com.em4n0101.mytvshows.networking

import android.net.ConnectivityManager
import android.net.NetworkCapabilities

class NetworkingStatusChecker(private val connectivityManager: ConnectivityManager?) {
    inline fun performIfConnectedToInternet(actionNotNetworkAvailable: () -> Unit, action: () -> Unit) {
        if (hasInternetConnection()) {
            action()
        } else {
            actionNotNetworkAvailable()
        }
    }

    fun hasInternetConnection(): Boolean {
        val network = connectivityManager?.activeNetwork ?: return false
        val capabilities = connectivityManager.getNetworkCapabilities(network) ?: return false

        return capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)
                || capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)
                || capabilities.hasTransport(NetworkCapabilities.TRANSPORT_VPN)
    }
}