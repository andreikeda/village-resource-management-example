package br.com.andreikeda.example.villageresourcemanagement.ui.home

class HomePresenter(
    private val view: HomeContract.View,
    private val router: HomeContract.Router
): HomeContract.Presenter {
    override fun onResume() {
        view.refreshData()
    }

    override fun onMenuItemBuildingsClicked() {
        view.setBuildingsTitle()
        router.showBuildingsFragment()
    }

    override fun onMenuItemResourceFieldsClicked() {
        view.setResourceFieldsTitle()
        router.showResourceFieldsFragment()
    }

    override fun onMenuItemVillageClicked() {
        view.setVillageTitle()
        router.showVillageFragment()
    }

    override fun onMenuItemUnitsClicked() {
        view.setUnitsTitle()
        router.showUnitsFragment()
    }
}