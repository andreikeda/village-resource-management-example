package br.com.andreikeda.example.villageresourcemanagement.domain.usecases

import br.com.andreikeda.example.villageresourcemanagement.domain.creators.VillageFactory
import br.com.andreikeda.example.villageresourcemanagement.domain.models.BuildingType.*
import br.com.andreikeda.example.villageresourcemanagement.domain.models.ResourceType.*
import br.com.andreikeda.example.villageresourcemanagement.domain.models.UnitType.*
import br.com.andreikeda.example.villageresourcemanagement.domain.models.Village
import org.junit.Test
import org.junit.Assert.assertEquals

class VillageUseCasesTest {
    @Test
    fun `test VillageUseCases construct() method with simple conditions`() {
        val useCase = VillageUseCases()
        val village = VillageFactory.newInstance().apply {
            resources[CLAY]?.quantity = 200
            resources[WOOD]?.quantity = 200
        }
        useCase.construct(village, WAREHOUSE)
        assertEquals("Village buildings should contain WAREHOUSE", village.buildings.containsKey(WAREHOUSE), true)
        assertEquals("Village buildings WAREHOUSE level should be 1", village.buildings[WAREHOUSE]?.level, 1)
    }

    @Test
    fun `test VillageUseCases construct() method with no resources availability condition`() {
        val useCase = VillageUseCases()
        val village = VillageFactory.newInstance()
        useCase.construct(village, WAREHOUSE)
        assertEquals("Village buildings should contain WAREHOUSE", village.buildings.containsKey(WAREHOUSE), false)
    }

    @Test
    fun `test VillageUseCases gatherResources() method`() {
        val useCase = VillageUseCases()
        val village = VillageFactory.newInstance()
        placeholderNewUnitIsBorn(village, useCase)
        useCase.gatherResources(village)
        assertEquals("We have resourceFields[CLAY], we sum it to the unit[POTTER].count value.", village.resources[CLAY]?.quantity, 2)
        assertEquals("We don't have resourceFields[IRON], it should be ZERO.", village.resources[IRON]?.quantity, 0)
        assertEquals("We have have resourceFields[WHEAT], we sum it to the unit[FARMER].count value.", village.resources[WHEAT]?.quantity, 2)
        assertEquals("We have resourceFields[WOOD], we sum it to the unit[LUMBERER].count value.", village.resources[WOOD]?.quantity, 2)
        assertEquals("We don't have resourceFields[MEAT], it should be ZERO.", village.resources[MEAT]?.quantity, 0)
        assertEquals("We have resourceFields[ROCK], we sum it to the unit[MASON].count value.", village.resources[ROCK]?.quantity, 1)
    }

    @Test
    fun `test VillageUseCases newUnitIsBorn() method`() {
        val useCase = VillageUseCases()
        val village = VillageFactory.newInstance()
        placeholderNewUnitIsBorn(village, useCase)
        assertEquals("As we created a new POTTER, it should increment the already existent unit[POTTER].count variable", village.units[POTTER]?.count, 1)
        assertEquals("As we don't have a resourceField[IRON], we can't create a new unit[MINER].", village.units[MINER]?.count, null)
        assertEquals("As we created a new FARMER, it should increment the already existent unit[FARMER].count variable.", village.units[FARMER]?.count, 1)
        assertEquals("As we created a new LUMBERER, it should increment the already existent unit[LUMBERER].count variable.", village.units[LUMBERER]?.count, 1)
        assertEquals("As we don't have a resourceField[MEAT], we can't create a new unit[HUNTER].", village.units[HUNTER]?.count, null)
        assertEquals("As we didn't create a new MASON, it shouldn't increment the unit[MASON].count variable.", village.units[MASON]?.count, null)
    }

    private fun placeholderNewUnitIsBorn(village: Village, useCases: VillageUseCases) = useCases.run {
        newUnitIsBorn(village, POTTER)
        newUnitIsBorn(village, MINER)
        newUnitIsBorn(village, FARMER)
        newUnitIsBorn(village, LUMBERER)
    }
}