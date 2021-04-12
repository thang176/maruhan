package com.dkt.mahuran.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.dkt.mahuran.data.ApiService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(val apiService: ApiService): ViewModel() {
    fun hello(){
        CoroutineScope(Dispatchers.Main).launch {
            val result = apiService.getHall("宮城県")
            if(result.isSuccessful){
                result.body()?.halls?.get(0)?.let {
                    Log.d("result: ", it.name)
                }
            }else{
                Log.d("result: ", result.errorBody().toString())
            }
        }
    }
}