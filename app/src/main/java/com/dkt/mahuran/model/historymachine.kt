package com.dkt.mahuran.model

import com.google.gson.annotations.SerializedName

data class MachineHistoryModel (
        val datas: List<DataHistory>
)

data class DataHistory (
        val date: String,
        val count: Int,
        val histories: List<History>
)

data class History (
        val time: String,

        @SerializedName("number_of_starts")
        val numberOfStarts: Int,

        @SerializedName("number_of_payouts")
        val numberOfPayouts: Int,

        @SerializedName("status_name")
        val statusName: String
)