package br.com.andreikeda.example.villageresourcemanagement.domain.models

import br.com.andreikeda.example.villageresourcemanagement.Constants.DAYS_LIMIT
import br.com.andreikeda.example.villageresourcemanagement.domain.creators.VillageFactory
import br.com.andreikeda.example.villageresourcemanagement.domain.models.BuildingType.GRANARY
import br.com.andreikeda.example.villageresourcemanagement.domain.models.BuildingType.MONUMENT
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
    enum class Status {
        GAME_OVER,
        ONGOING,
        VICTORY
    }
    private val villageUseCase: VillageCommands = VillageUseCases()

    private lateinit var village: Village

    var daysPlayed = 0

    fun endDay(): Status {
        daysPlayed++
        if (daysPlayed >= DAYS_LIMIT) {
            return Status.GAME_OVER
        }
        if (village.buildings.containsKey(MONUMENT)) {
            return Status.VICTORY
        }
        villageUseCase.run {
            gatherResources(village)
            consumeWheat(village)
            // todo: call other methods
        }
        return Status.ONGOING
    }

    fun constructBuilding(buildingType: BuildingType) =
        villageUseCase.construct(village, buildingType)

    fun constructResourceField(resourceType: ResourceType) =
        villageUseCase.construct(village, resourceType)

    fun getBuildingLevel(buildingType: BuildingType) =
        village.buildings[buildingType]?.level ?: 0

    fun getResourceFieldLevel(resourceType: ResourceType) =
        village.resourceFields[resourceType]?.level ?: 0

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

    fun hasResourceField(resourceType: ResourceType) =
        village.resourceFields.containsKey(resourceType)

    fun start() {
        village = VillageFactory.newInstance()
    }

    fun upgradeBuilding(buildingType: BuildingType) =
        villageUseCase.upgradeBuilding(village, buildingType)

    fun upgradeResourceField(resourceType: ResourceType) =
        villageUseCase.upgradeResourceField(village, resourceType)

    private fun resourceQuantity(resourceType: ResourceType) =
        villageUseCase.resourceQuantity(village, resourceType)

    private fun storageCapacity(resourceType: ResourceType) =
        when (resourceType) {
            WHEAT -> villageUseCase.storageCapacity(village, village.buildings[GRANARY])
            else -> villageUseCase.storageCapacity(village, village.buildings[WAREHOUSE])
        }
}