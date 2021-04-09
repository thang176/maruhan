package com.dkt.mahuran

import android.util.Log
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(): ViewModel() {
    fun hello(){
        Log.d("ABC", "hello")
    }

    suspend fun getRequestAsync(): String{
        delay(5000)
        return "OKOKOK"
    }

    fun getRequestSync(): String{
        Thread.sleep(5000)
        return "OKOKOK"
    }
}