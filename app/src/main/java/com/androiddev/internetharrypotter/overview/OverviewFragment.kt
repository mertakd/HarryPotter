package com.androiddev.internetharrypotter.overview

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.androiddev.internetharrypotter.R
import com.androiddev.internetharrypotter.databinding.FragmentOverviewBinding


class OverviewFragment : Fragment() {

    private lateinit var binding: FragmentOverviewBinding

    private val viewModel: OverviewViewModel by lazy { OverviewViewModel()}

    private val characterAdapter by lazy { CharacterAdapter() }

    override fun onCreateView(
        //burada view ile ilgili işlemler yaptığımızda bir geçikme veya bir problem yaşandığında null dönebiliyor ve uygulama çökebiliyor
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentOverviewBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //databinding izni
        binding.lifecycleOwner = this

        binding.viewModelXml = viewModel

        binding.rvCharacters.adapter = characterAdapter

        characterAdapter.onClick = {
            viewModel.displayCharacterDetail(it)
        }

        viewModel.navigateToSelectedCharater.observe(viewLifecycleOwner, Observer {

            if (it != null){
                this.findNavController().navigate(OverviewFragmentDirections.actionOverviewFragmentToDetailFragment(it))
                viewModel.displayCharacterDetailComplete()
            }
        })

        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.overflow_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        viewModel.filterCharacters(
            when(item.itemId) {
                R.id.show_gryffindor -> HarryPotterApiFilter.SHOW_GRYFFINDOR
                R.id.show_hufflepuff -> HarryPotterApiFilter.SHOW_HUFFLEPUF
                R.id.show_revenclaw -> HarryPotterApiFilter.SHOW_RAVENCLAW
                else -> HarryPotterApiFilter.SHOW_SLYTHERIN
            }
        )

        return true
    }


}