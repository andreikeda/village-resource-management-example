package br.com.andreikeda.example.villageresourcemanagement.domain

enum class ResourceType {
    ROCK, IRON, CLAY, WOOD, MEAT, CROP
}

data class Resource(val type: ResourceType, var quantity: Int)