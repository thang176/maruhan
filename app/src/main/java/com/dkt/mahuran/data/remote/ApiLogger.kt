package com.dkt.mahuran.data.remote

import android.util.Log
import com.google.gson.GsonBuilder
import com.google.gson.JsonParser
import com.google.gson.JsonSyntaxException
import okhttp3.logging.HttpLoggingInterceptor

class ApiLogger : HttpLoggingInterceptor.Logger {
    override fun log(message: String) {
        if (message.startsWith("{") || message.startsWith("[")) {
            try {
                val prettyPrintJson = GsonBuilder().setPrettyPrinting()
                    .create().toJson(JsonParser().parse(message))
                Log.d("Mii-Log", prettyPrintJson)
            } catch (m: JsonSyntaxException) {
                Log.d("Mii-Log", message)

            }
        } else {
            Log.d("Mii-Log", message)
        }
    }
}