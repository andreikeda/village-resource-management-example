package br.com.andreikeda.example.villageresourcemanagement.ui.buildings.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.andreikeda.example.villageresourcemanagement.R
import br.com.andreikeda.example.villageresourcemanagement.databinding.ItemCardBuildingBinding
import br.com.andreikeda.example.villageresourcemanagement.domain.models.BuildingType
import br.com.andreikeda.example.villageresourcemanagement.domain.models.Game
import br.com.andreikeda.example.villageresourcemanagement.ui.buildings.BuildingsContract

class BuildingsAdapter(private val presenter: BuildingsContract.Presenter):
    RecyclerView.Adapter<BuildingsAdapter.ViewHolder>() {

    class ViewHolder(private val binding: ItemCardBuildingBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(presenter: BuildingsContract.Presenter, buildingType: BuildingType) {
            val context = binding.root.context
            binding.apply {
                tvBuildingName.text = buildingType.name
                if (Game.hasBuilding(buildingType)) {
                    tvBuildingLevel.text = context.getString(R.string.formatted_level_value, Game.getBuildingLevel(buildingType))
                    tvBuildingStatus.text = context.getString(R.string.label_building_built)
                    btBuild.isEnabled = false
                    btUpgrade.isEnabled = true
                } else {
                    tvBuildingLevel.text = ""
                    tvBuildingStatus.text = context.getString(R.string.label_building_not_built)
                    btBuild.isEnabled = true
                    btUpgrade.isEnabled = false
                }
                btBuild.setOnClickListener { presenter.onItemBuildClicked(buildingType) }
                btUpgrade.setOnClickListener { presenter.onItemUpgradeClicked(buildingType) }
            }
        }
    }

    override fun getItemCount() =
        BuildingType.values().size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(presenter, BuildingType.values()[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemCardBuildingBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }
}