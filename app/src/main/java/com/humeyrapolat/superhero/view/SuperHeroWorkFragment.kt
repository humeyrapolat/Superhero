package com.humeyrapolat.superhero.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.humeyrapolat.superhero.model.Result
import com.humeyrapolat.superhero.R
import com.humeyrapolat.superhero.databinding.FragmentSuperHeroWorkBinding
import com.humeyrapolat.superhero.viewmodel.SuperHeroWorkViewModel


/**
 * @author Humeyra Polat
 * @sınce 26.08.2021
 */
class SuperHeroWorkFragment : Fragment() {

    private lateinit var result :Result
    private lateinit var dataBinding: FragmentSuperHeroWorkBinding
    private lateinit var viewmodel: SuperHeroWorkViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        dataBinding =
            DataBindingUtil.inflate(
                inflater,
                R.layout.fragment_super_hero_work,
                container, false
            )
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewmodel = ViewModelProviders.of(this).get(SuperHeroWorkViewModel::class.java)

        arguments?.let {
            result = SuperHeroWorkFragmentArgs.fromBundle(it).result!!
            println(result)
        }
        dataBinding.selectedSuperHero = result
    }

}