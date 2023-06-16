package br.com.andreikeda.example.villageresourcemanagement.ui.units.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.andreikeda.example.villageresourcemanagement.R
import br.com.andreikeda.example.villageresourcemanagement.databinding.ItemCardUnitBinding
import br.com.andreikeda.example.villageresourcemanagement.domain.models.Game
import br.com.andreikeda.example.villageresourcemanagement.domain.models.UnitType
import br.com.andreikeda.example.villageresourcemanagement.ui.units.UnitsContract

class UnitsAdapter(private val presenter: UnitsContract.Presenter):
    RecyclerView.Adapter<UnitsAdapter.ViewHolder>() {

    class ViewHolder(private val binding: ItemCardUnitBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(presenter: UnitsContract.Presenter, unitType: UnitType) {
            val context = binding.root.context
            binding.apply {
                tvUnitName.text = unitType.name
                tvUnitQuantity.text = context.getString(R.string.formatted_unit_quantity, Game.getUnitQuantity(unitType))
            }
        }
    }

    override fun getItemCount() =
        UnitType.values().size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(presenter, UnitType.values()[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemCardUnitBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }
}