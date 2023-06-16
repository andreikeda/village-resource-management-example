package br.com.andreikeda.example.villageresourcemanagement.domain.creators

import br.com.andreikeda.example.villageresourcemanagement.domain.models.BuildingType
import org.junit.Test
import org.junit.Assert.assertEquals

class BuildingFactoryTest {
    @Test
    fun `test BuildingFactory newInstance() method`() {
        val building = BuildingFactory.newInstance(BuildingType.BARRACKS)
        assertEquals(building.type, BuildingType.BARRACKS)
        assertEquals(building.level, 1)
    }
}