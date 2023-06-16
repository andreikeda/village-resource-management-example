package br.com.andreikeda.example.villageresourcemanagement.domain.creators

import br.com.andreikeda.example.villageresourcemanagement.domain.models.BuildingType.CITY_CENTER
import br.com.andreikeda.example.villageresourcemanagement.domain.models.Resource
import br.com.andreikeda.example.villageresourcemanagement.domain.models.ResourceType.*
import br.com.andreikeda.example.villageresourcemanagement.domain.models.UnitType.*
import br.com.andreikeda.example.villageresourcemanagement.domain.models.Village

object VillageFactory {
    fun newInstance() =
        Village(
            mutableMapOf(),
            mutableMapOf(),
            mutableMapOf(),
            mutableMapOf()
        ).apply {
            // Creating buildings
            buildings[CITY_CENTER] = BuildingFactory.newInstance(CITY_CENTER)
            // Creating resource fields
            resourceFields[ROCK] = ResourceFieldFactory.newInstance(ROCK)
            resourceFields[WHEAT] = ResourceFieldFactory.newInstance(WHEAT)
            resourceFields[CLAY] = ResourceFieldFactory.newInstance(CLAY)
            resourceFields[WOOD] = ResourceFieldFactory.newInstance(WOOD)
            // Setting resources
            resources[ROCK] = Resource(ROCK, 0)
            resources[WHEAT] = Resource(WHEAT, 0)
            resources[MEAT] = Resource(MEAT, 0)
            resources[WOOD] = Resource(WOOD, 0)
            resources[CLAY] = Resource(CLAY, 0)
            resources[IRON] = Resource(IRON, 0)
        }
}