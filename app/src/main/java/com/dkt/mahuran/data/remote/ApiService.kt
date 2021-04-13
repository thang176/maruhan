package com.dkt.mahuran.data.remote

import com.dkt.mahuran.HistoryGraphMachineModel
import com.dkt.mahuran.model.*
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface ApiService {

    @GET("/api/v1.7.3/hall/search/area")
    suspend fun getHall(@Query("region") region: String): Response<HallModel>

    @GET("/api/v1.7.3/hall/machine/models")
    suspend fun getHallChild(
        @Query("hall_code") hallCode: String,
        @Query("ps_type") psType: String,
        @Query("type") type: Int
    ): Response<ChildHallModel>

    @GET("/api/v1.7.3/hall/machines")
    suspend fun getMachines(
        @Query("hall_code") hallCode: String,
        @Query("model_code") modelCode: String,
        @Query("date") date: String
    ): Response<MachineModel>

    @GET("/api/v1.7.3/machine/info")
    suspend fun getMachineInfo(
        @Query("hall_code") hallCode: String,
        @Query("machine_id") machineId: String,
        @Query("date") dates: List<String>
    ): Response<InfoMachineModel>

    @GET("/api/v1.7.3/machine/slump")
    suspend fun getMachineSlump(
        @Query("hall_code") hallCode: String,
        @Query("machine_id") machineId: String,
        @Query("date") dates: List<String>
    ): Response<SlumpMachineModel>

    @GET("/api/v1.7.3/machine/history/graph")
    suspend fun getMachineHistoryGraph(
        @Query("hall_code") hallCode: String,
        @Query("machine_id") machineId: String,
        @Query("date") dates: List<String>
    ): Response<HistoryGraphMachineModel>

    @GET("/api/v1.7.3/machine/history")
    suspend fun getMachineHistory(
        @Query("hall_code") hallCode: String,
        @Query("machine_id") machineId: String,
        @Query("date") dates: List<String>
    ): Response<MachineHistoryModel>

}