package com.dkt.mahuran.model

class Demo {
data class abc(
    val datas: List<Data>
)

data class Data(
    val count: Int,
    val date: String,
    val histories: List<History>
)

data class History(
    val number_of_payouts: Int,
    val number_of_starts: Int,
    val status_name: String,
    val time: String
)}