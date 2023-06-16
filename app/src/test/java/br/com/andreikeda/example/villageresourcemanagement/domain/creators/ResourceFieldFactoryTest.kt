package br.com.andreikeda.example.villageresourcemanagement.domain.creators

import br.com.andreikeda.example.villageresourcemanagement.domain.models.ResourceType.ROCK
import org.junit.Test
import org.junit.Assert.assertEquals

class ResourceFieldFactoryTest {
    @Test
    fun `test ResourceFieldFactory newInstance() method`() {
        val resourceField = ResourceFieldFactory.newInstance(ROCK)
        assertEquals(resourceField.type, ROCK)
        assertEquals(resourceField.level, 1)
    }
}