package br.com.andreikeda.example.villageresourcemanagement.ui.village

import br.com.andreikeda.example.villageresourcemanagement.domain.models.Game
import br.com.andreikeda.example.villageresourcemanagement.ui.home.HomeContract

class VillagePresenter(private val view: HomeContract.View): VillageContract.Presenter {
    override fun onGatherResourcesClicked() {
        Game.endDay()
        view.refreshData()
    }
}