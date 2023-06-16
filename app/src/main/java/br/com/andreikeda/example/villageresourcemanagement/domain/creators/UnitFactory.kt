package br.com.andreikeda.example.villageresourcemanagement.domain.creators

import br.com.andreikeda.example.villageresourcemanagement.domain.models.Unit
import br.com.andreikeda.example.villageresourcemanagement.domain.models.UnitType

object UnitFactory {
    fun newInstance(unitType: UnitType) =
        Unit(unitType,1)
}