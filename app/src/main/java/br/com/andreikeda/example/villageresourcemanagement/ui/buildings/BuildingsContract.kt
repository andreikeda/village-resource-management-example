package br.com.andreikeda.example.villageresourcemanagement.ui.buildings

import br.com.andreikeda.example.villageresourcemanagement.domain.models.BuildingType

interface BuildingsContract {
    interface View {
        fun showBuildErrorDialog(buildingType: BuildingType)
        fun showBuildSuccessDialog(buildingType: BuildingType)
        fun showUpgradeErrorDialog(buildingType: BuildingType)
        fun showUpgradeSuccessDialog(buildingType: BuildingType)
    }

    interface Presenter {
        fun onItemBuildClicked(buildingType: BuildingType)
        fun onItemUpgradeClicked(buildingType: BuildingType)
    }
}