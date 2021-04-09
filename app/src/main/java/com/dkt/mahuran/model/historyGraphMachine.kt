package com.dkt.mahuran

import com.google.gson.annotations.SerializedName

data class HistoryGraphMachineModel (
        val datas: List<DataHistory>
)

data class DataHistory (
        val date: String,
        val items: List<Item>
)

data class Item (
        val status: String,
        val value: Int,
        @SerializedName("number_of_bonuses")
        val numberOfBonuses: Int
)