package br.com.andreikeda.example.villageresourcemanagement.ui.resourcefields

import br.com.andreikeda.example.villageresourcemanagement.domain.models.Game
import br.com.andreikeda.example.villageresourcemanagement.domain.models.ResourceType
import br.com.andreikeda.example.villageresourcemanagement.ui.home.HomeContract

class ResourceFieldsPresenter(
    private val parentView: HomeContract.View,
    private val view: ResourceFieldsContract.View
) : ResourceFieldsContract.Presenter {
    override fun onItemBuildClicked(resourceType: ResourceType) {
        if (Game.constructResourceField(resourceType)) {
            view.run {
                showBuildSuccessDialog(resourceType)
                notifyDataSetChanged()
            }
            parentView.refreshData()
        } else {
            view.showBuildErrorDialog(resourceType)
        }
    }

    override fun onItemUpgradeClicked(resourceType: ResourceType) {
        if (Game.upgradeResourceField(resourceType)) {
            view.run {
                showUpgradeSuccessDialog(resourceType)
                notifyDataSetChanged()
            }
            parentView.refreshData()
        } else {
            view.showUpgradeErrorDialog(resourceType)
        }
    }
}