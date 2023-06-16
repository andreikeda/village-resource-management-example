package br.com.andreikeda.example.villageresourcemanagement.domain.creators

import br.com.andreikeda.example.villageresourcemanagement.domain.models.BuildingType.*
import br.com.andreikeda.example.villageresourcemanagement.domain.models.ResourceType.*
import org.junit.Test
import org.junit.Assert.assertEquals

class VillageFactoryTest {

    @Test
    fun `test VillageFactory newInstance() method`() {
        val village = VillageFactory.newInstance()
        assertEquals("Initially, the village should contain only one building", village.buildings.count(), 1)
        assertEquals("Initially, the village should contain CITY_CENTER", village.buildings.containsKey(CITY_CENTER), true)
        assertEquals("Initially, the village shouldn't contain BARRACKS", village.buildings.containsKey(BARRACKS), false)
        assertEquals("Initially, the village shouldn't contain GRANARY", village.buildings.containsKey(GRANARY), false)
        assertEquals("Initially, the village shouldn't contain WAREHOUSE", village.buildings.containsKey(WAREHOUSE), false)
        assertEquals("Initially, the CITY_CENTER level should be 1", village.buildings[CITY_CENTER]?.level, 1)
        assertEquals("Initially, the village should contain 4 resource fields", village.resourceFields.count(), 4)
        assertEquals("Initially, the village should contain WHEAT resource field", village.resourceFields.containsKey(WHEAT), true)
        assertEquals("Initially, the village should contain ROCK resource field", village.resourceFields.containsKey(ROCK), true)
        assertEquals("Initially, the village should contain WOOD resource field", village.resourceFields.containsKey(WOOD), true)
        assertEquals("Initially, the village should contain CLAY resource field", village.resourceFields.containsKey(CLAY), true)
        assertEquals("Initially, the village should not contain IRON resource field", village.resourceFields.containsKey(IRON), false)
        assertEquals("Initially, the village should not contain MEAT resource field", village.resourceFields.containsKey(MEAT), false)
    }
}