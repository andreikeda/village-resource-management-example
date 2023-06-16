package br.com.andreikeda.example.villageresourcemanagement.ui.village.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.andreikeda.example.villageresourcemanagement.databinding.ItemCardVillageBinding

class VillageAdapter : RecyclerView.Adapter<VillageAdapter.ViewHolder>() {
    enum class CardType {
        BUILDINGS,
        UNITS,
        RESOURCE_FIELDS
    }

    class ViewHolder(private val binding: ItemCardVillageBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(cardType: CardType) {
            when (cardType) {
                CardType.RESOURCE_FIELDS -> {
                    binding.tvCardTitle.text = "Resource Fields"
                    binding.tvCardDescription.text = "Resource Fields Description"
                }
                CardType.BUILDINGS -> {
                    binding.tvCardTitle.text = "Buildings"
                    binding.tvCardDescription.text = "Buildings Description"
                }
                CardType.UNITS -> {
                    binding.tvCardTitle.text = "Units"
                    binding.tvCardDescription.text = "Units Description"
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