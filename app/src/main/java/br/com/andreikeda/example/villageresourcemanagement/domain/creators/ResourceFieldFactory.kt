package br.com.andreikeda.example.villageresourcemanagement.domain.creators

import br.com.andreikeda.example.villageresourcemanagement.domain.models.ResourceField
import br.com.andreikeda.example.villageresourcemanagement.domain.models.ResourceType

object ResourceFieldFactory {
    fun newInstance(resourceType: ResourceType) =
        ResourceField(resourceType, 1)
}