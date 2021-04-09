package com.dkt.mahuran.model

import com.google.gson.annotations.SerializedName

data class HallModel(
        @SerializedName("count")
        val count: Int,
        @SerializedName("halls")
        val halls: List<HallItem>
)

data class HallItem(
        @SerializedName("code")
        val code: String,
        @SerializedName("name")
        val name: String,
        @SerializedName("add")
        val address: String,
        @SerializedName("lat")
        val lat: Long,
        @SerializedName("long")
        val lng: Long,
        @SerializedName("machine_invisibled")
        val machineInvisibled: Int
)