package br.com.andreikeda.example.villageresourcemanagement.ui.resourcefields

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.andreikeda.example.villageresourcemanagement.R
import br.com.andreikeda.example.villageresourcemanagement.databinding.FragmentResourceFieldBinding
import br.com.andreikeda.example.villageresourcemanagement.domain.models.ResourceType
import br.com.andreikeda.example.villageresourcemanagement.ui.home.HomeContract
import br.com.andreikeda.example.villageresourcemanagement.ui.resourcefields.adapter.ResourceFieldsAdapter
import com.google.android.material.snackbar.Snackbar

class ResourceFieldsFragment: Fragment(), ResourceFieldsContract.View {
    private lateinit var binding: FragmentResourceFieldBinding
    private lateinit var presenter: ResourceFieldsContract.Presenter

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is HomeContract.View) {
            presenter = ResourceFieldsPresenter(context, this)
        } else {
            throw IllegalArgumentException("Activity must implement HomeContract.View")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentResourceFieldBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = ResourceFieldsAdapter(presenter)
        }
    }

    override fun notifyDataSetChanged() {
        binding.recyclerView.adapter?.notifyDataSetChanged()
    }

    override fun showBuildErrorDialog(resourceType: ResourceType) {
        showSnackBar(getString(R.string.formatted_feedback_build_error, resourceType.name))
    }

    override fun showBuildSuccessDialog(resourceType: ResourceType) {
        showSnackBar(getString(R.string.formatted_feedback_build_success, resourceType.name))
    }

    override fun showUpgradeErrorDialog(resourceType: ResourceType) {
        showSnackBar(getString(R.string.formatted_feedback_upgrade_error, resourceType.name))
    }

    override fun showUpgradeSuccessDialog(resourceType: ResourceType) {
        showSnackBar(getString(R.string.formatted_feedback_upgrade_success, resourceType.name))
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