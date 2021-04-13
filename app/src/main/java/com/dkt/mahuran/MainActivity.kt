package com.dkt.mahuran

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import com.dkt.mahuran.data.local.MaruhanDatabase
import com.dkt.mahuran.data.local.dao.HallDao
import com.dkt.mahuran.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    val mainViewModel: MainViewModel by viewModels()

    @FlowPreview
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainViewModel.getMachineInfo(prg)

    }

    suspend fun combineFlow() {
        val flow = flowOf(1, 2)
        val flow2 = flowOf("a", "b", "c")
        flow.combine(flow2) { i, s -> i.toString() + s }.collect {
            println(it) // Will print "1a 2a 2b 2c"
        }
    }

    suspend fun zipFlow() {
        val flow1 = (1..3).asFlow()
        val flow2 = listOf<String>("a", "b", "c").onEach { delay(1000) }.asFlow()

        flow1.zip(flow2) { f1, f2 ->
            val strv = "${f1} - ${f2}"
            strv
        }.collect {
            println(it)
        }
    }

    fun demoMaruhan1() {
        CoroutineScope(Dispatchers.IO).launch {
            val listDe: ArrayList<Deferred<Int>> = ArrayList()

            for (i in 0..14) {
                val deferred = async {
                    fetch1(i)
                }

                listDe.add(deferred)
            }

            val listRe = listDe.awaitAll()

            for (item in listRe) {
                Log.d("B: ", item.toString())
            }
        }
    }

    suspend fun fetch1(id: Int): Int {
        delay(2000)
        Log.d("A: ", id.toString())
        return id;
    }

    suspend fun fetch2() {
        delay(2000)
        Log.d("A: ", 2.toString())
    }

    suspend fun flow1() {
        flow {
            emit(1)
            withContext(Dispatchers.IO) {
                emit(2)
            }
        }.flowOn(Dispatchers.IO)
            .collect {
                println(it)
            }
    }

    @FlowPreview
    suspend fun coroutine1() {
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