package com.dkt.mahuran.model

import com.google.gson.annotations.SerializedName

data class MachineModel(
        @SerializedName("child_halls")
        val childHalls: List<ChillHalls>
)

data class ChillHalls(
        @SerializedName("four_yen")
        val fourYen: FourYen,
        @SerializedName("light")
        val light: Light,
        @SerializedName("date")
        val date: String,
        @SerializedName("child_hall_code")
        val childHallCode: String,
        @SerializedName("child_hall_name")
        val childHallName: String,
        @SerializedName("toku_rate_flag")
        val tokuRateFlag: Boolean
)

data class Light(
        @SerializedName("group_count")
        val groupCount: Int
)

data class FourYen(
        @SerializedName("group_count")
        val groupCount: Int,
        @SerializedName("groups")
        val groups: List<FourYenGroup>
)

data class FourYenGroup(
        @SerializedName("group_name")
        val groupName: String,
        @SerializedName("count")
        val count: Int,
        @SerializedName("machines")
        val machines: List<FourYenGroupMachines>
)

data class FourYenGroupMachines(
        @SerializedName("machine_number")
        val machineNumber: String,
        @SerializedName("machine_id")
        val machineId: String,
        @SerializedName("number_of_all_starts")
        val numberOfAllStarts: Int,
        @SerializedName("available")
        val available: Boolean,
        @SerializedName("bonus_number")
        val bonusNumber: Int,
        @SerializedName("toku_rate_h")
        val tokuRateH: Int,
        @SerializedName("toku_rate_l")
        val tokuRateL: Int,
        @SerializedName("bonus_prob")
        val bonusProb: Int
)