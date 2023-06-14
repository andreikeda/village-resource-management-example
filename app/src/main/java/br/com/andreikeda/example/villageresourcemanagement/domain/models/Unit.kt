package br.com.andreikeda.example.villageresourcemanagement.domain

enum class UnitType {
    COLLECTOR, WARRIOR, ARCHER, FARMER
}

data class Unit(val type: UnitType, var count: Int)