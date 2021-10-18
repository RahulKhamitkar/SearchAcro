package com.rahul.acro.utils

import android.annotation.SuppressLint
import android.content.Context
import android.net.ConnectivityManager
import android.widget.Toast

class NetworkConnection {


    companion  object{
        fun isNetworkAvailable(context: Context){
            val cm =
                context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val activeNetwork = cm.activeNetworkInfo
            if (activeNetwork != null) { // connected to the internet
                if (activeNetwork.type == ConnectivityManager.TYPE_WIFI) {
                } else if (activeNetwork.type == ConnectivityManager.TYPE_MOBILE) {
                }
            } else {
                // not connected to the internet
                Toast.makeText(context, "No Internet Connection", Toast.LENGTH_LONG).show()

            }
        }
    }
}