package ir.mostafaghanbari.localtime.controllers

import ir.mostafaghanbari.localtime.models.StateModel

class States(private val listener: StateListener) {

    interface StateListener {
        fun result(data: List<StateModel>)
    }

    fun getCountries() {
        listener.result(ArrayList<StateModel>().apply {
            add(StateModel(1, "canada"))
            add(StateModel(2, "Russia"))
            add(StateModel(3, "U.S.A"))
        })
    }

    fun getCities(country:StateModel){
        val cities = ArrayList<StateModel>().apply {
            add(StateModel(4, "St.john`s", "GMT-3:30", 1))
            add(StateModel(5, "calgary", "GMT-7:00", 1))
            add(StateModel(6, "vancouver", "GMT-8:00", 1))
            add(StateModel(7, "Moscow", "GMT+3:00", 2))
            add(StateModel(8, "Novosibirsk", "GMT+7:00", 2))
            add(StateModel(9, "Chokurdakh", "GMT+11:00", 2))
            add(StateModel(10, "New York", "GMT-5:00", 3))
            add(StateModel(11, "El Paso", "GMT-7:00", 3))
            add(StateModel(12, "Los Angeles", "GMT-8:00", 3))
        }

        listener.result(cities.filter { c -> c.parentId == country._id })

    }

}