package br.com.andreikeda.example.villageresourcemanagement.ui.resourcefields.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.andreikeda.example.villageresourcemanagement.R
import br.com.andreikeda.example.villageresourcemanagement.databinding.ItemCardResourceFieldBinding
import br.com.andreikeda.example.villageresourcemanagement.domain.models.Game
import br.com.andreikeda.example.villageresourcemanagement.domain.models.ResourceType
import br.com.andreikeda.example.villageresourcemanagement.ui.resourcefields.ResourceFieldsContract.Presenter

class ResourceFieldsAdapter(private val presenter: Presenter):
    RecyclerView.Adapter<ResourceFieldsAdapter.ViewHolder>() {

    class ViewHolder(private val binding: ItemCardResourceFieldBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(presenter: Presenter, resourceFieldType: ResourceType) {
            val context = binding.root.context
            binding.apply {
                tvResourceFieldName.text = resourceFieldType.name
                if (Game.hasResourceField(resourceFieldType)) {
                    tvResourceFieldLevel.text = context.getString(R.string.formatted_level_value, Game.getResourceFieldLevel(resourceFieldType))
                    tvResourceFieldStatus.text = context.getString(R.string.label_resource_field_available)
                    btBuild.isEnabled = false
                    btUpgrade.isEnabled = true
                } else {
                    tvResourceFieldLevel.text = ""
                    tvResourceFieldStatus.text = context.getString(R.string.label_resource_field_unavailable)
                    btBuild.isEnabled = true
                    btUpgrade.isEnabled = false
                }
                btBuild.setOnClickListener { presenter.onItemBuildClicked(resourceFieldType) }
                btUpgrade.setOnClickListener { presenter.onItemUpgradeClicked(resourceFieldType) }
            }
        }
    }

    override fun getItemCount() =
        ResourceType.values().size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(presenter, ResourceType.values()[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemCardResourceFieldBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }
}