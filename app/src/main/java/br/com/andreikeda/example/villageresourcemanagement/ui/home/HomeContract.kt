package br.com.andreikeda.example.villageresourcemanagement.ui.home

interface HomeContract {
    interface View {
        fun refreshData()
        fun setBuildingsTitle()
        fun setVillageTitle()
        fun setUnitsTitle()
    }

    interface Presenter {
        fun onResume()
        fun onMenuItemBuildingsClicked()
        fun onMenuItemVillageClicked()
        fun onMenuItemUnitsClicked()
    }

    interface Router {
        fun showBuildingsFragment()
        fun showVillageFragment()
        fun showUnitsFragment()
    }
}