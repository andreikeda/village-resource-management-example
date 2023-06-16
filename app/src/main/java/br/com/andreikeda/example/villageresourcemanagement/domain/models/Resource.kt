package br.com.andreikeda.example.villageresourcemanagement.domain.models

enum class ResourceType {
    ROCK, IRON, CLAY, WOOD, MEAT, WHEAT
}

data class Resource(val type: ResourceType, var quantity: Int)

data class ResourceField(val type: ResourceType, var level: Int)