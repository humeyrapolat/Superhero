package com.humeyrapolat.superhero.view

import android.os.Bundle
import android.os.Parcelable
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import com.humeyrapolat.superhero.R
import com.humeyrapolat.superhero.databinding.FragmentSuperHeroAppearanceBinding
import com.humeyrapolat.superhero.model.Result
import com.humeyrapolat.superhero.viewmodel.SuperHeroAppearanceViewModel
import kotlinx.android.synthetic.main.fragment_super_hero_appearance.*
import kotlinx.android.synthetic.main.fragment_super_heroes.*

/**
 * @author Humeyra Polat
 * @sınce 24.08.2021
 */
class SuperHeroAppearanceFragment : Fragment() {

    private var resultId : Long =0
    private lateinit var viewModel: SuperHeroAppearanceViewModel
    private lateinit var dataBinding: FragmentSuperHeroAppearanceBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        dataBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_super_hero_appearance,
            container,
            false
        )
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(SuperHeroAppearanceViewModel::class.java)

        arguments?.let {
            resultId = SuperHeroAppearanceFragmentArgs.fromBundle(it).resultId

        }

        viewModel.getIdData(resultId)
        observeLiveData()

        button_work.setOnClickListener {view ->
            val result = dataBinding.selectedSuperHero
            val action = SuperHeroAppearanceFragmentDirections.actionSuperHeroDetailToSuperHeroWork(result)
            Navigation.findNavController(view).navigate(action)
        }

    }

    private fun observeLiveData() {

        viewModel.superHeroAppearanceLiveData.observe(viewLifecycleOwner, Observer { superHero ->
            superHero?.let {
                dataBinding.selectedSuperHero = superHero
                progressBar.visibility=View.GONE
                coordinatorId.visibility= View.VISIBLE

            }
        })

        viewModel.loadingLiveData.observe(viewLifecycleOwner , Observer {loading->
            loading?.let {
                if (it){
                    progressBar.visibility=View.VISIBLE
                    coordinatorId.visibility= View.GONE

                }else{
                    progressBar.visibility=View.GONE
                    coordinatorId.visibility= View.VISIBLE


                }
            }
        })

    }


}