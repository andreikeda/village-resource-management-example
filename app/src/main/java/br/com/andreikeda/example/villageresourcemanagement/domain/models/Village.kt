package br.com.andreikeda.example.villageresourcemanagement.domain.models

data class Village(
    val resources: MutableMap<ResourceType, Resource>,
    val resourceFields: MutableMap<ResourceType, ResourceField>,
    val buildings: MutableMap<BuildingType, Building>,
    val units: MutableMap<UnitType, Unit>
)