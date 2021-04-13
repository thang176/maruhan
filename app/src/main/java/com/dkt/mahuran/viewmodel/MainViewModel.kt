package com.dkt.mahuran.viewmodel

import android.util.Log
import android.view.View
import android.widget.ProgressBar
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
class MainViewModel @Inject constructor(val apiService: ApiService, val hallDao: HallDao) :
    ViewModel() {

    @FlowPreview
    fun getMachineInfo(prg: ProgressBar) {
        CoroutineScope(Dispatchers.IO).launch {
            prg.visibility = View.VISIBLE
            var count = 0

            listRegion.asFlow()
                .flatMapMerge {
                    val result = apiService.getHall(it).body()
                    val listHallModel = result?.halls ?: listOf()
                    listHallModel.asFlow()
                }.onCompletion {
                    prg.visibility = View.INVISIBLE
                    if(it != null){
                        val exception = it as Throwable

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
                    val code = hallDao.getHallCode(it.code)
                    if (code.isNullOrEmpty()) {
                        val entity = convertModelToEntity(it)
                        hallDao.insertAll(entity)
                    }
                }
        }
    }

    fun convertModelToEntity(hallItem: HallItem): HallEntity {
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