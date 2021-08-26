package com.humeyrapolat.superhero.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.humeyrapolat.superhero.adapter.SuperHeroesAdapter
import com.humeyrapolat.superhero.R
import com.humeyrapolat.superhero.viewmodel.SuperHeroesViewModel
import kotlinx.android.synthetic.main.fragment_super_hero_appearance.*
import kotlinx.android.synthetic.main.fragment_super_heroes.*

class SuperHeroesFragment : Fragment() {

    private lateinit var viewmodel: SuperHeroesViewModel
    private val superHeroAdapter = SuperHeroesAdapter(arrayListOf())


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_super_heroes, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewmodel = ViewModelProviders.of(this).get(SuperHeroesViewModel::class.java)
        viewmodel.getData()
        super_heroes_recycler.layoutManager = GridLayoutManager(context, 2)
        super_heroes_recycler.adapter = superHeroAdapter
        observeLiveData()
    }


    private fun observeLiveData(){

        viewmodel.superHeroesLiveData.observe(viewLifecycleOwner, Observer { superHero ->
            superHero.let {
                super_heroes_recycler.visibility = View.VISIBLE
                superHeroAdapter.setListItem(superHero)
            }
        })
    }

}