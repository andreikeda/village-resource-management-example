package br.com.andreikeda.example.villageresourcemanagement.domain.models

enum class BuildingType {
    GRANARY, BARRACKS, WAREHOUSE, CITY_CENTER
}

data class Building(val type: BuildingType, var level: Int)