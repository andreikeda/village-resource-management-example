package br.com.andreikeda.example.villageresourcemanagement.ui.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import br.com.andreikeda.example.villageresourcemanagement.R
import br.com.andreikeda.example.villageresourcemanagement.databinding.ActivityHomeBinding
import br.com.andreikeda.example.villageresourcemanagement.domain.models.Game
import br.com.andreikeda.example.villageresourcemanagement.ui.buildings.BuildingsFragment
import br.com.andreikeda.example.villageresourcemanagement.ui.units.UnitsFragment
import br.com.andreikeda.example.villageresourcemanagement.ui.village.VillageFragment

class HomeActivity: AppCompatActivity(), HomeContract.View, HomeContract.Router {
    private lateinit var binding: ActivityHomeBinding
    private lateinit var mPresenter: HomeContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mPresenter = HomePresenter(this, this)
        Game.start()

        binding.bottomNavigation.run {
            setOnItemSelectedListener { menuItem ->
                when (menuItem.itemId) {
                    R.id.menu_buildings -> mPresenter.onMenuItemBuildingsClicked()
                    R.id.menu_village -> mPresenter.onMenuItemVillageClicked()
                    R.id.menu_units -> mPresenter.onMenuItemUnitsClicked()
                }
                true
            }
            selectedItemId = R.id.menu_village
        }

        supportActionBar?.elevation = 0f
    }

    override fun onResume() {
        super.onResume()

        mPresenter.onResume()
    }

    override fun refreshData() {
        setTopView()
    }

    override fun setBuildingsTitle() {
        setTitle(R.string.title_buildings)
    }

    override fun setVillageTitle() {
        setTitle(R.string.title_village)
    }

    override fun setUnitsTitle() {
        setTitle(R.string.title_units)
    }

    override fun showBuildingsFragment() {
        replaceFragment(BuildingsFragment())
    }

    override fun showVillageFragment() {
        replaceFragment(VillageFragment())
    }

    override fun showUnitsFragment() {
        replaceFragment(UnitsFragment())
    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(binding.container.id, fragment)
            .commit()
    }

    private fun setTopView() {
        binding.run {
            tvDaysPlayed.text = getString(R.string.formatted_days_played, Game.daysPlayed)
            tvResourceClay.text =
                getString(
                    R.string.formatted_resource_clay_value,
                    Game.getResourceClayQuantity(),
                    Game.getResourceClayCapacity()
                )
            tvResourceIron.text =
                getString(
                    R.string.formatted_resource_iron_value,
                    Game.getResourceIronQuantity(),
                    Game.getResourceIronCapacity()
                )
            tvResourceMeat.text =
                getString(
                    R.string.formatted_resource_meat_value,
                    Game.getResourceMeatQuantity(),
                    Game.getResourceMeatCapacity()
                )
            tvResourceRock.text =
                getString(
                    R.string.formatted_resource_rock_value,
                    Game.getResourceRockQuantity(),
                    Game.getResourceRockCapacity()
                )
            tvResourceWheat.text =
                getString(
                    R.string.formatted_resource_wheat_value,
                    Game.getResourceWheatQuantity(),
                    Game.getResourceWheatCapacity()
                )
            tvResourceWood.text =
                getString(
                    R.string.formatted_resource_wood_value,
                    Game.getResourceWoodQuantity(),
                    Game.getResourceWoodCapacity()
                )
        }
    }
}