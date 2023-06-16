package br.com.andreikeda.example.villageresourcemanagement.ui.buildings

import br.com.andreikeda.example.villageresourcemanagement.domain.models.BuildingType
import br.com.andreikeda.example.villageresourcemanagement.domain.models.Game
import br.com.andreikeda.example.villageresourcemanagement.ui.home.HomeContract

class BuildingsPresenter(
    private val parentView: HomeContract.View,
    private val view: BuildingsContract.View
): BuildingsContract.Presenter {
    override fun onItemBuildClicked(buildingType: BuildingType) {
        if (Game.constructBuilding(buildingType)) {
            view.run {
                showBuildSuccessDialog(buildingType)
                notifyDataSetChanged()
            }
            parentView.refreshData()
        } else {
            view.showBuildErrorDialog(buildingType)
        }
    }

    override fun onItemUpgradeClicked(buildingType: BuildingType) {
        if (Game.upgradeBuilding(buildingType)) {
            view.run {
                showUpgradeSuccessDialog(buildingType)
                notifyDataSetChanged()
            }
            parentView.refreshData()
        } else {
            view.showUpgradeErrorDialog(buildingType)
        }
    }
}