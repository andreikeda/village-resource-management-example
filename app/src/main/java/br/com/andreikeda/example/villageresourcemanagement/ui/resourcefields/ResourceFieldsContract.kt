package br.com.andreikeda.example.villageresourcemanagement.ui.resourcefields

import br.com.andreikeda.example.villageresourcemanagement.domain.models.ResourceType

interface ResourceFieldsContract {
    interface View {
        fun notifyDataSetChanged()
        fun showBuildErrorDialog(resourceType: ResourceType)
        fun showBuildSuccessDialog(resourceType: ResourceType)
        fun showUpgradeErrorDialog(resourceType: ResourceType)
        fun showUpgradeSuccessDialog(resourceType: ResourceType)
    }

    interface Presenter {
        fun onItemBuildClicked(resourceType: ResourceType)
        fun onItemUpgradeClicked(resourceType: ResourceType)
    }
}