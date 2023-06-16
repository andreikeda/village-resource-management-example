package br.com.andreikeda.example.villageresourcemanagement.ui.units

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.andreikeda.example.villageresourcemanagement.databinding.FragmentUnitsBinding
import br.com.andreikeda.example.villageresourcemanagement.ui.units.adapter.UnitsAdapter

class UnitsFragment: Fragment() {
    private lateinit var binding: FragmentUnitsBinding
    private lateinit var presenter: UnitsContract.Presenter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentUnitsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter = UnitsPresenter()

        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = UnitsAdapter(presenter)
        }
    }
}