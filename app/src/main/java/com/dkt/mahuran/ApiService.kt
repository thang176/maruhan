package com.dkt.mahuran

import com.dkt.mahuran.model.*
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("/hall/search/area")
    suspend fun getHall(@Query("region") region: String): Response<HallModel>

    @GET("/hall/machine/models")
    suspend fun getHallChild(@Query("hall_code") hallCode: String,
                             @Query("ps_type") psType: String,
                             @Query("type") type: Int): Response<ChildHallModel>

    @GET("/hall/machines")
    suspend fun getMachines(@Query("hall_code") hallCode: String,
                            @Query("model_code") modelCode: String,
                            @Query("date") date: String): Response<MachineModel>

    @GET("/machine/info")
    suspend fun getMachineInfo(@Query("hall_code") hallCode: String,
                               @Query("machine_id") machineId: String,
                               @Query("date") dates: List<String>): Response<InfoMachineModel>

    @GET("/machine/slump")
    suspend fun getMachineSlump(@Query("hall_code") hallCode: String,
                                @Query("machine_id") machineId: String,
                                @Query("date") dates: List<String>): Response<SlumpMachineModel>

    @GET("/machine/history/graph")
    suspend fun getMachineHistoryGraph(@Query("hall_code") hallCode: String,
                                       @Query("machine_id") machineId: String,
                                       @Query("date") dates: List<String>): Response<HistoryGraphMachineModel>

    @GET("/machine/history")
    suspend fun getMachineHistory(@Query("hall_code") hallCode: String,
                                  @Query("machine_id") machineId: String,
                                  @Query("date") dates: List<String>): Response<MachineHistoryModel>

}