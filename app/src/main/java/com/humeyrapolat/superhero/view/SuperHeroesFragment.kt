package com.humeyrapolat.superhero.view

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.*
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.humeyrapolat.superhero.R
import com.humeyrapolat.superhero.adapter.SuperHeroesAdapter
import com.humeyrapolat.superhero.viewmodel.SuperHeroesViewModel
import kotlinx.android.synthetic.main.fragment_super_hero_appearance.*
import kotlinx.android.synthetic.main.fragment_super_heroes.*

/**
 * @author Humeyra Polat
 * @sınce 24.08.2021
 */
class SuperHeroesFragment : Fragment() {

    private lateinit var viewmodel: SuperHeroesViewModel
    private val superHeroAdapter = SuperHeroesAdapter(arrayListOf())
    var search : CharSequence? = ""



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_super_heroes, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewmodel = ViewModelProviders.of(this).get(SuperHeroesViewModel::class.java)
        viewmodel.getData()
        super_heroes_recycler.layoutManager = GridLayoutManager(context, 2)
        super_heroes_recycler.adapter = superHeroAdapter
        observeLiveData()


        search_editText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                superHeroAdapter.filter.filter(s)
                search = s
            }

            override fun afterTextChanged(s: Editable?) {
            }

        })

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