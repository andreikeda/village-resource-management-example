package br.com.andreikeda.example.villageresourcemanagement.domain.creators

import br.com.andreikeda.example.villageresourcemanagement.domain.models.Building
import br.com.andreikeda.example.villageresourcemanagement.domain.models.BuildingType

object BuildingFactory {
    fun newInstance(buildingType: BuildingType) =
        Building(buildingType, 1)
}