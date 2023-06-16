package br.com.andreikeda.example.villageresourcemanagement.ui.village

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.andreikeda.example.villageresourcemanagement.databinding.FragmentVillageBinding
import br.com.andreikeda.example.villageresourcemanagement.ui.home.HomeContract
import br.com.andreikeda.example.villageresourcemanagement.ui.village.adapter.VillageAdapter

class VillageFragment: Fragment() {
    private lateinit var binding: FragmentVillageBinding
    private lateinit var presenter: VillageContract.Presenter

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is HomeContract.View) {
            presenter = VillagePresenter(context)
        } else {
            throw IllegalArgumentException("Activity must implement HomeContract.View")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentVillageBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.run {
            recyclerView.apply {
                layoutManager = LinearLayoutManager(requireContext())
                adapter = VillageAdapter()
            }
            btGatherResources.setOnClickListener { presenter.onGatherResourcesClicked() }
        }
    }
}