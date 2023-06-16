package br.com.andreikeda.example.villageresourcemanagement.ui.home

interface HomeContract {
    interface View {
        fun gameOver()
        fun refreshData()
        fun setBuildingsTitle()
        fun setResourceFieldsTitle()
        fun setUnitsTitle()
        fun setVillageTitle()
        fun victory()
    }

    interface Presenter {
        fun onResume()
        fun onMenuItemBuildingsClicked()
        fun onMenuItemResourceFieldsClicked()
        fun onMenuItemUnitsClicked()
        fun onMenuItemVillageClicked()
        fun restartGame()
    }

    interface Router {
        fun showBuildingsFragment()
        fun showResourceFieldsFragment()
        fun showUnitsFragment()
        fun showVillageFragment()
    }
}