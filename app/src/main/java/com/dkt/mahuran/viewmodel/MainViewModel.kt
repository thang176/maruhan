package com.dkt.mahuran.viewmodel

import android.util.Log
import android.view.View
import android.widget.ProgressBar
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dkt.mahuran.data.local.dao.HallDao
import com.dkt.mahuran.data.local.entity.HallEntity
import com.dkt.mahuran.data.remote.ApiService
import com.dkt.mahuran.model.HallItem
import com.dkt.mahuran.utils.listRegion
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import java.util.*
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val apiService: ApiService,
    private val hallDao: HallDao
) : ViewModel() {

    var checkProgressBar: MutableLiveData<Int> = MutableLiveData()

    @FlowPreview
    fun getMachineInfo(prg: ProgressBar) {
        CoroutineScope(Dispatchers.IO).launch {
            var count = 0

            listRegion.asFlow()
                .flatMapMerge {
                    val result = apiService.getHall(it).body()
                    val listHallModel = result?.halls ?: listOf()
                    if (listHallModel.isNotEmpty()) {
                        for (item in listHallModel) {
                            val code = hallDao.getHallCode(item.code)
                            if (code.isNullOrEmpty()) {
                                val entity = convertModelToEntity(item)
                                hallDao.insertAll(entity)
                            }
                        }
                    }
                    listHallModel.asFlow()
                }.onCompletion {
                    prg.visibility = View.INVISIBLE
                    if (it != null) {
                        val throwable = it
                        throwable.message?.let { it1 -> Log.d("Err", it1) }
                    }
                }
//                .flatMapMerge { hallModel ->
//                    val res = apiService.getHallChild(hallModel.code, "P", 2)
//                    val hallChildList = res.body()?.childHalls ?: listOf()
//                    val result = hallChildList
//                        .map { it.models }
//                        .map { childList ->
//                            var text = "${hallModel.code} - "
//                            childList.map {
//                                text += "${it.modelCode}, "
//                                it.hallCode = hallModel.code
//                            }
//                            Log.d("Mii", text)
//                            childList
//                        }.asFlow()
//                    result
//                }.flatMapMerge { listHallModelItem ->
//                    listHallModelItem.asFlow()
//                        .flatMapMerge {
//                            val result =
//                                apiService.getMachines(it.hallCode, it.modelCode, "20210412")
//                            val listMachine = result.body()?.childHalls ?: listOf()
//                            listMachine.asFlow()
//                        }
//
//                }
                .collect {
                    count++
                    println(count)
                }
        }
    }

    private fun convertModelToEntity(hallItem: HallItem): HallEntity {
        val id = UUID.randomUUID().toString()
        return HallEntity(
            id,
            hallItem.code,
            hallItem.name,
            hallItem.address,
            hallItem.lat,
            hallItem.lng,
            hallItem.machineInvisibled
        )
    }
}