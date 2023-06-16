package br.com.andreikeda.example.villageresourcemanagement.ui.village.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.andreikeda.example.villageresourcemanagement.R
import br.com.andreikeda.example.villageresourcemanagement.databinding.ItemCardVillageBinding
import br.com.andreikeda.example.villageresourcemanagement.domain.models.Game

class VillageAdapter : RecyclerView.Adapter<VillageAdapter.ViewHolder>() {
    enum class CardType {
        BUILDINGS,
        UNITS,
        RESOURCE_FIELDS
    }

    class ViewHolder(private val binding: ItemCardVillageBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(cardType: CardType) {
            val context = binding.root.context
            when (cardType) {
                CardType.RESOURCE_FIELDS -> {
                    binding.tvCardTitle.text = context.getString(R.string.title_resource_fields)
                    binding.tvCardDescription.text =
                        context.getString(
                            R.string.formatted_available_unavailable,
                            Game.getResourceFieldsBuilt().keys.toString(),
                            Game.getResourceFieldsNotBuilt().toString()
                        )
                }
                CardType.BUILDINGS -> {
                    binding.tvCardTitle.text = context.getString(R.string.title_buildings)
                    binding.tvCardDescription.text =
                        context.getString(
                            R.string.formatted_available_unavailable,
                            Game.getBuildingsBuilt().keys.toString(),
                            Game.getBuildingsNotBuilt().toString()
                        )
                }
                CardType.UNITS -> {
                    binding.tvCardTitle.text = context.getString(R.string.title_units)
                    binding.tvCardDescription.text =
                        context.getString(
                            R.string.formatted_available_unavailable,
                            Game.getUnitsAvailable().keys.toString(),
                            Game.getUnitsUnavailable().toString()
                        )
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemCardVillageBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount() =
        CardType.values().size

    override fun getItemViewType(position: Int) =
        getCardType(position).ordinal

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getCardType(position))
    }

    private fun getCardType(position: Int) =
        when (position) {
            0 -> CardType.BUILDINGS
            1 -> CardType.UNITS
            2 -> CardType.RESOURCE_FIELDS
            else -> throw IllegalArgumentException("Invalid position: $position")
        }
}