package com.dkt.mahuran.viewmodel

import android.util.Log
import android.view.View
import android.widget.ProgressBar
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dkt.mahuran.data.local.dao.HallDao
import com.dkt.mahuran.data.local.dao.MachineDao
import com.dkt.mahuran.data.local.dao.ModelDao
import com.dkt.mahuran.data.local.entity.HallEntity
import com.dkt.mahuran.data.local.entity.ModelEntity
import com.dkt.mahuran.data.remote.ApiService
import com.dkt.mahuran.model.ChildHallModelItem
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
    private val hallDao: HallDao,
    private val modelDao: ModelDao,
    private val machineDao: MachineDao
) : ViewModel() {

    @FlowPreview
    fun getMachineInfo(prg: ProgressBar) {
        CoroutineScope(Dispatchers.IO).launch {
            var countHall = 0
            var countModel = 0
            var countMachine = 0
            var checkComplete = false

            listRegion.asFlow()
                .flatMapMerge {
                    countHall++
                    val result = apiService.getHall(it).body()
                    val listHallModel = result?.halls ?: listOf()
                    if (listHallModel.isNotEmpty()) {
                        for (item in listHallModel) {
                            val code = hallDao.getHallCode(item.code)
                            if (code.isNullOrEmpty()) {
                                val entity = convertHallModelToHallEntity(item)
                                hallDao.insertAll(entity)
                            }
                        }
                    }
                    listHallModel.asFlow()
                }.flatMapMerge { hallModel ->
                    countModel++
                    val res = apiService.getHallChild(hallModel.code, "P", 2)
                    val hallChildList = res.body()?.childHalls ?: listOf()
                    val result = hallChildList
                        .map { it.models }
                        .map { childList ->
                            childList.map {
                                val modelCodeOld = modelDao.getModelCode(it.modelCode)
                                if (modelCodeOld.isNullOrEmpty()) {
                                    val entity = convertToModelEntity(it)
                                    modelDao.insertModel(entity)
                                }
                                it.hallCode = hallModel.code
                            }
                            childList
                        }.asFlow()
                    result
                }.flatMapMerge { listHallModelItem ->
                    listHallModelItem.asFlow()
                        .flatMapMerge {
                            countMachine++
                            val result =
                                apiService.getMachines(it.hallCode, it.modelCode, "20210412")
                            val listMachine = result.body()?.childHalls ?: listOf()
                            listMachine.asFlow()
                        }

                }.onCompletion {
                    checkComplete = true
                    prg.visibility = View.INVISIBLE
                    if (it != null) {
                        val throwable = it
                        throwable.message?.let { it1 -> Log.e("Err", it1) }
                    }
                }.collect()

            withContext(Dispatchers.Main) {
                if (checkComplete) {
                    Log.d("Mii-complete", "$countHall - $countModel - $countMachine")
                }
            }
        }
    }

    private fun convertHallModelToHallEntity(hallItem: HallItem): HallEntity {
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

    private fun convertToModelEntity(childHallModelItem: ChildHallModelItem): ModelEntity {
        val id = UUID.randomUUID().toString()
        return ModelEntity(
            id,
            childHallModelItem.modelCode,
            childHallModelItem.officialNumber,
            childHallModelItem.modelName,
            childHallModelItem.count
        )
    }
}