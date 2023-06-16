package br.com.andreikeda.example.villageresourcemanagement.ui.home

interface HomeContract {
    interface View {
        fun refreshData()
        fun setBuildingsTitle()
        fun setResourceFieldsTitle()
        fun setUnitsTitle()
        fun setVillageTitle()
    }

    interface Presenter {
        fun onResume()
        fun onMenuItemBuildingsClicked()
        fun onMenuItemResourceFieldsClicked()
        fun onMenuItemUnitsClicked()
        fun onMenuItemVillageClicked()
    }

    interface Router {
        fun showBuildingsFragment()
        fun showResourceFieldsFragment()
        fun showUnitsFragment()
        fun showVillageFragment()
    }
}