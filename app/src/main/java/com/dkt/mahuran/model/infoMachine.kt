package com.dkt.mahuran.model

import com.google.gson.annotations.SerializedName

data class InfoMachineModel (
        @SerializedName("img_url")
        val imgURL: String,

        @SerializedName("machine_id")
        val machineID: List<String>,

        @SerializedName("machine_number")
        val machineNumber: List<String>,

        val datas: List<DataInfo>
)

data class DataInfo (
        val date: String,

        @SerializedName("number_of_bbs")
        val numberOfBBS: Int,

        @SerializedName("number_of_rbs")
        val numberOfRbs: Int,

        @SerializedName("number_of_arts")
        val numberOfArts: Int,

        @SerializedName("number_of_starts")
        val numberOfStarts: Int,

        @SerializedName("number_of_all_starts")
        val numberOfAllStarts: Int,

        @SerializedName("number_of_bonuses")
        val numberOfBonuses: Int,

        val prob: Int,

        @SerializedName("bb_prob")
        val bbProb: Int,

        @SerializedName("rb_prob")
        val rbProb: Int,

        @SerializedName("art_prob")
        val artProb: Int,

        @SerializedName("total_prob")
        val totalProb: Int,

        @SerializedName("bonus_prob")
        val bonusProb: Int,

        @SerializedName("max_number_of_stocks")
        val maxNumberOfStocks: Int,

        val invisibled: Int,

        @SerializedName("invisibled_bonus")
        val invisibledBonus: Int,

        @SerializedName("toku_rate_h")
        val tokuRateH: Int
)