package ir.mostafaghanbari.localtime.models

import androidx.annotation.Keep

@Keep
data class StateModel(
    val _id: Int,
    val name: String,
    val timeZone: String? = null,
    val parentId: Int = -1
)