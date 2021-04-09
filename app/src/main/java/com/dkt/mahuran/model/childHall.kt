package com.dkt.mahuran.model

import com.google.gson.annotations.SerializedName

data class ChildHallModel(
        @SerializedName("child_halls")
        val childHalls: List<ChildHallItem>
)

data class ChildHallItem(
        @SerializedName("child_hall_code")
        val childHallCode: String,
        @SerializedName("child_hall_name")
        val childHallName: String,
        @SerializedName("machine_invisibled")
        val machineInvisibled: Boolean,
        @SerializedName("models")
        val models: List<ChildHallModelItem>
)

data class ChildHallModelItem(
        @SerializedName("model_code")
        val modelCode: String,
        @SerializedName("official_number")
        val officialNumber: String,
        @SerializedName("model_name")
        val modelName: String,
        @SerializedName("count")
        val count: Int
)