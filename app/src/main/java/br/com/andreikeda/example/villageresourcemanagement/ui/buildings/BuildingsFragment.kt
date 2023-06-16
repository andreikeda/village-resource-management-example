package br.com.andreikeda.example.villageresourcemanagement.ui.buildings

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.andreikeda.example.villageresourcemanagement.R
import br.com.andreikeda.example.villageresourcemanagement.databinding.FragmentBuildingBinding
import br.com.andreikeda.example.villageresourcemanagement.domain.models.BuildingType
import br.com.andreikeda.example.villageresourcemanagement.ui.buildings.adapter.BuildingsAdapter
import br.com.andreikeda.example.villageresourcemanagement.ui.home.HomeContract
import br.com.andreikeda.example.villageresourcemanagement.ui.village.VillagePresenter
import com.google.android.material.snackbar.Snackbar

class BuildingsFragment: Fragment(), BuildingsContract.View {
    private lateinit var binding: FragmentBuildingBinding
    private lateinit var presenter: BuildingsContract.Presenter

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is HomeContract.View) {
            presenter = BuildingsPresenter(context, this)
        } else {
            throw IllegalArgumentException("Activity must implement HomeContract.View")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentBuildingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = BuildingsAdapter(presenter)
        }
    }

    override fun notifyDataSetChanged() {
        binding.recyclerView.adapter?.notifyDataSetChanged()
    }

    override fun showBuildErrorDialog(buildingType: BuildingType) {
        showSnackBar(getString(R.string.formatted_feedback_build_error, buildingType.name))
    }

    override fun showBuildSuccessDialog(buildingType: BuildingType) {
        showSnackBar(getString(R.string.formatted_feedback_build_success, buildingType.name))
    }

    override fun showUpgradeErrorDialog(buildingType: BuildingType) {
        showSnackBar(getString(R.string.formatted_feedback_upgrade_error, buildingType.name))
    }

    override fun showUpgradeSuccessDialog(buildingType: BuildingType) {
        showSnackBar(getString(R.string.formatted_feedback_upgrade_success, buildingType.name))
    }

    private fun showSnackBar(text: String) {
        Snackbar.make(
            binding.root,
            text,
            Snackbar.LENGTH_INDEFINITE
        ).apply {
            setAction(R.string.button_dismiss) {
                dismiss()
            }
        }.show()
    }
}