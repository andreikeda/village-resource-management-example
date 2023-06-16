package br.com.andreikeda.example.villageresourcemanagement.domain.usecases

import br.com.andreikeda.example.villageresourcemanagement.Constants
import br.com.andreikeda.example.villageresourcemanagement.domain.creators.BuildingFactory
import br.com.andreikeda.example.villageresourcemanagement.domain.models.Building
import br.com.andreikeda.example.villageresourcemanagement.domain.models.BuildingType
import br.com.andreikeda.example.villageresourcemanagement.domain.models.BuildingType.*
import br.com.andreikeda.example.villageresourcemanagement.domain.models.ResourceField
import br.com.andreikeda.example.villageresourcemanagement.domain.models.ResourceType
import br.com.andreikeda.example.villageresourcemanagement.domain.models.ResourceType.*
import br.com.andreikeda.example.villageresourcemanagement.domain.models.Unit
import br.com.andreikeda.example.villageresourcemanagement.domain.models.UnitType
import br.com.andreikeda.example.villageresourcemanagement.domain.models.UnitType.*
import br.com.andreikeda.example.villageresourcemanagement.domain.models.Village

interface VillageCommands {
    fun construct(village: Village, buildingType: BuildingType): Boolean
    fun consumeWheat(village: Village)
    fun gatherResources(village: Village)
    fun newUnitIsBorn(village: Village, unitType: UnitType)
    fun resourceQuantity(village: Village, resourceType: ResourceType): Int
    fun storageCapacity(village: Village, building: Building?): Int
    fun unitDies(village: Village, unitType: UnitType)
    fun upgradeBuilding(village: Village, buildingType: BuildingType): Boolean
}

class VillageUseCases: VillageCommands {

    override fun construct(village: Village, buildingType: BuildingType): Boolean {
        val building = village.buildings[buildingType]
        if (building == null) {
            val requiredResources = getBuildingUpgradeCost(buildingType, 1)
            if (hasSufficientResources(village, requiredResources)) {
                consumeResources(village, requiredResources)
                village.buildings[buildingType] = BuildingFactory.newInstance(buildingType)
                println("Successfully created ${buildingType.name}")
            } else {
                println("Insufficient resources to upgrade ${buildingType.name}")
            }
        } else {
            println("Building already constructed!")
        }
        return village.buildings.containsKey(buildingType)
    }

    override fun consumeWheat(village: Village) {
        village.resources[WHEAT]?.let { resource ->
            resource.quantity -= village.units.count()
            if (resource.quantity <= 0) {
                unitDies(village, village.units.keys.random())
            }
        }
    }

    override fun gatherResources(village: Village) {
        village.apply {
            resources[IRON]?.apply {
                quantity += getCollectorCount(village, MINER) + (resourceFields[IRON]?.level ?: 0)
                quantity = quantity.coerceAtMost(storageCapacity(village, buildings[WAREHOUSE]))
            }
            resources[WHEAT]?.apply {
                quantity += getCollectorCount(village, FARMER) + (resourceFields[WHEAT]?.level ?: 0)
                quantity = quantity.coerceAtMost(storageCapacity(village, buildings[GRANARY]))
            }
            resources[MEAT]?.apply {
                quantity += getCollectorCount(village, HUNTER) + (resourceFields[MEAT]?.level ?: 0)
                quantity = quantity.coerceAtMost(storageCapacity(village, buildings[WAREHOUSE]))
            }
            resources[CLAY]?.apply {
                quantity += getCollectorCount(village, POTTER) + (resourceFields[CLAY]?.level ?: 0)
                quantity = quantity.coerceAtMost(storageCapacity(village, buildings[WAREHOUSE]))
            }
            resources[ROCK]?.apply {
                quantity += getCollectorCount(village, MASON) + (resourceFields[ROCK]?.level ?: 0)
                quantity = quantity.coerceAtMost(storageCapacity(village, buildings[WAREHOUSE]))
            }
            resources[WOOD]?.apply {
                quantity += getCollectorCount(village, LUMBERER) + (resourceFields[WOOD]?.level ?: 0)
                quantity = quantity.coerceAtMost(storageCapacity(village, buildings[WAREHOUSE]))
            }
        }
    }

    override fun newUnitIsBorn(village: Village, unitType: UnitType) {
        village.units[unitType]?.let {
            it.count++
        } ?: run {
            if (hasResourceFieldRequirement(unitType, village.resourceFields)) {
                village.units[unitType] = Unit(unitType, 1)
            }
        }
    }

    override fun resourceQuantity(village: Village, resourceType: ResourceType) =
        village.resources[resourceType]?.quantity ?: 0

    override fun storageCapacity(village: Village, building: Building?) =
        building?.let { Constants.CAPACITY_ARRAY[it.level] } ?: run { Constants.MIN_CAPACITY }

    override fun unitDies(village: Village, unitType: UnitType) {
        village.units[unitType]?.let {
            it.count--
            if (it.count <= 0) {
                village.units.remove(unitType)
            }
        }
    }

    override fun upgradeBuilding(village: Village, buildingType: BuildingType): Boolean {
        val building = village.buildings[buildingType]
        if (building != null) {
            val requiredResources = getBuildingUpgradeCost(buildingType, building.level)
            if (hasSufficientResources(village, requiredResources)) {
                consumeResources(village, requiredResources)
                building.level++
                village.buildings[buildingType] = building
                println("Successfully upgraded ${buildingType.name} to level ${building.level}")
                return true
            } else {
                println("Insufficient resources to upgrade ${buildingType.name}")
            }
        } else {
            println("Invalid building type")
        }
        return false
    }

    private fun getCollectorCount(village: Village, unitType: UnitType) =
        village.units[unitType]?.count ?: 0

    private fun getBuildingUpgradeCost(buildingType: BuildingType, currentLevel: Int) =
        when (buildingType) {
            BARRACKS -> mapOf(
                ROCK to 3 * currentLevel,
                WOOD to 2 * currentLevel
            )
            WAREHOUSE -> mapOf(
                CLAY to 2 * currentLevel,
                WOOD to 1 * currentLevel
            )
            GRANARY -> mapOf(
                CLAY to 2 * currentLevel,
                WOOD to 1 * currentLevel
            )
            CITY_CENTER -> mapOf(
                ROCK to 2 * currentLevel,
                WOOD to 1 * currentLevel,
                CLAY to 1 * currentLevel
            )
            MONUMENT -> mapOf(
                ROCK to 1000,
                WOOD to 1000,
                CLAY to 1000,
                WHEAT to 500,
                IRON to 250,
                MEAT to 250
            )
        }

    private fun hasResourceFieldRequirement(
        unitType: UnitType,
        resourceFields: MutableMap<ResourceType, ResourceField>
    ) =
        when (unitType) {
            MINER -> resourceFields.containsKey(IRON)
            MASON -> resourceFields.containsKey(ROCK)
            LUMBERER -> resourceFields.containsKey(WOOD)
            POTTER -> resourceFields.containsKey(CLAY)
            HUNTER -> resourceFields.containsKey(MEAT)
            FARMER -> resourceFields.containsKey(WHEAT)
        }

    private fun hasSufficientResources(village: Village, requiredResources: Map<ResourceType, Int>) =
        requiredResources.all { (resourceType, requiredQuantity) ->
            village.resources[resourceType]?.quantity ?: 0 >= requiredQuantity
        }

    private fun consumeResources(village: Village, requiredResources: Map<ResourceType, Int>) {
        requiredResources.forEach { (resourceType, requiredQuantity) ->
            village.resources[resourceType]?.quantity = village.resources[resourceType]?.quantity?.minus(requiredQuantity) ?: 0
        }
    }
}