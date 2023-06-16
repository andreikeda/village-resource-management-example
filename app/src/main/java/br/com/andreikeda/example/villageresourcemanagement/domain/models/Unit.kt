package br.com.andreikeda.example.villageresourcemanagement.domain.models

enum class UnitType {
    FARMER, // Collects WHEAT
    MINER, // Collects IRON
    MASON, // Collects ROCK
    HUNTER, // Collects MEAT
    LUMBERER, // Collects WOOD
    POTTER // Collects CLAY
}

data class Unit(val type: UnitType, var count: Int)