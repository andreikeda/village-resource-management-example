package br.com.andreikeda.example.villageresourcemanagement.domain

enum class BuildingType {
    RESOURCE_FIELD, BARRACKS, WAREHOUSE
}

data class Building(val type: BuildingType, var level: Int)