package com.dkt.mahuran.model

data class SlumpMachineModel (
        val datas: List<Data>
)

data class Data (
        val date: String,
        val min: Int,
        val max: Int,
        val items: List<Item>
)

data class Item (
        val time: String,
        val value: Int
)