package br.com.andreikeda.example.villageresourcemanagement.domain.models

import br.com.andreikeda.example.villageresourcemanagement.domain.creators.VillageFactory
import br.com.andreikeda.example.villageresourcemanagement.domain.models.BuildingType.GRANARY
import br.com.andreikeda.example.villageresourcemanagement.domain.models.BuildingType.WAREHOUSE
import br.com.andreikeda.example.villageresourcemanagement.domain.models.ResourceType.CLAY
import br.com.andreikeda.example.villageresourcemanagement.domain.models.ResourceType.IRON
import br.com.andreikeda.example.villageresourcemanagement.domain.models.ResourceType.MEAT
import br.com.andreikeda.example.villageresourcemanagement.domain.models.ResourceType.ROCK
import br.com.andreikeda.example.villageresourcemanagement.domain.models.ResourceType.WHEAT
import br.com.andreikeda.example.villageresourcemanagement.domain.models.ResourceType.WOOD
import br.com.andreikeda.example.villageresourcemanagement.domain.usecases.VillageCommands
import br.com.andreikeda.example.villageresourcemanagement.domain.usecases.VillageUseCases

object Game {
    private val villageUseCase: VillageCommands = VillageUseCases()

    private lateinit var village: Village

    var daysPlayed = 0

    fun endDay() {
        daysPlayed++
        villageUseCase.run {
            gatherResources(village)
            consumeWheat(village)
            if ((village.resources[WHEAT]?.quantity ?: 0) > 0) {
                // todo: create a random unit
            }
            // todo: call other methods
        }
    }

    fun constructBuilding(buildingType: BuildingType) =
        villageUseCase.construct(village, buildingType)

    fun getBuildingLevel(buildingType: BuildingType) =
        village.buildings[buildingType]?.level ?: 0

    fun getResourceClayCapacity() =
        storageCapacity(CLAY)

    fun getResourceClayQuantity() =
        resourceQuantity(CLAY)

    fun getResourceIronCapacity() =
        storageCapacity(IRON)

    fun getResourceIronQuantity() =
        resourceQuantity(IRON)

    fun getResourceMeatCapacity() =
        storageCapacity(MEAT)

    fun getResourceMeatQuantity() =
        resourceQuantity(MEAT)

    fun getResourceRockCapacity() =
        storageCapacity(ROCK)

    fun getResourceRockQuantity() =
        resourceQuantity(ROCK)

    fun getResourceWheatCapacity() =
        storageCapacity(WHEAT)

    fun getResourceWheatQuantity() =
        resourceQuantity(WHEAT)

    fun getResourceWoodCapacity() =
        storageCapacity(WOOD)

    fun getResourceWoodQuantity() =
        resourceQuantity(WOOD)

    fun getUnitQuantity(unitType: UnitType) =
        village.units[unitType]?.count ?: 0

    fun hasBuilding(buildingType: BuildingType) =
        village.buildings.containsKey(buildingType)

    fun start() {
        village = VillageFactory.newInstance()
    }

    fun upgradeBuilding(buildingType: BuildingType) =
        villageUseCase.upgradeBuilding(village, buildingType)

    private fun resourceQuantity(resourceType: ResourceType) =
        villageUseCase.resourceQuantity(village, resourceType)

    private fun storageCapacity(resourceType: ResourceType) =
        when (resourceType) {
            WHEAT -> villageUseCase.storageCapacity(village, village.buildings[GRANARY])
            else -> villageUseCase.storageCapacity(village, village.buildings[WAREHOUSE])
        }
}