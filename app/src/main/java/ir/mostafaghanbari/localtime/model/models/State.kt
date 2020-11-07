package ir.mostafaghanbari.localtime.model.models

data class StateModel(
    val _id: Int,
    val name: String,
    val parentId: Int = -1
)