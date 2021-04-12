package com.dkt.mahuran

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import com.dkt.mahuran.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    val mainViewModel: MainViewModel by viewModels()

    @FlowPreview
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        CoroutineScope(Dispatchers.Main).launch {
//            val data = mainViewModel.getRequestAsync()
//            txt_hello.text = data
//        }
//
        mainViewModel.hello()

//        CoroutineScope(Dispatchers.Main).launch {
//            flow1()
//        }
//        demoMaruhan1()
//        CoroutineScope(Dispatchers.Main).launch {
//            coroutine1()
//        }
    }

    fun demoMaruhan1(){
        CoroutineScope(Dispatchers.IO).launch {
            val listDe: ArrayList<Deferred<Int>> = ArrayList()

            for (i in 0..14) {
                val deferred = async {
                    fetch1(i)
                }

                listDe.add(deferred)
            }

            val listRe = listDe.awaitAll()

            for (item in listRe){
                Log.d("B: ", item.toString())
            }
        }
    }

    suspend fun fetch1(id: Int): Int{
        delay(2000)
        Log.d("A: ", id.toString())
        return id;
    }

    suspend fun fetch2(){
        delay(2000)
        Log.d("A: ", 2.toString())
    }

    suspend fun flow1(){
        flow {
            emit(1)
            withContext(Dispatchers.IO){
                emit(2)
            }
        }.flowOn(Dispatchers.IO)
            .collect {
                println(it)
            }
    }

    @FlowPreview
    suspend fun coroutine1(){
        (1..15).asFlow().flatMapMerge {
            delay(2000)
            println("A : $it")
            flow {
                delay(2000)
                emit(it)
            }
        }.collect {
            println("B: $it")
        }
    }
}