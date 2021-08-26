package com.humeyrapolat.superhero.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.humeyrapolat.superhero.model.Result
import com.humeyrapolat.superhero.model.SuperHeroListener
import com.humeyrapolat.superhero.R
import com.humeyrapolat.superhero.view.SuperHeroesFragmentDirections
import com.humeyrapolat.superhero.databinding.SuperHeroItemBinding
import com.humeyrapolat.superhero.view.SuperHeroAppearanceFragmentDirections
import kotlinx.android.synthetic.main.super_hero_item.view.*

class SuperHeroesAdapter(var heroList: ArrayList<Result>) :
    RecyclerView.Adapter<SuperHeroesAdapter.SuperHeroHolder>(),SuperHeroListener {

    class SuperHeroHolder(var view: SuperHeroItemBinding) : RecyclerView.ViewHolder(view.root) {
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SuperHeroHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = DataBindingUtil.inflate<SuperHeroItemBinding>(
            inflater,
            R.layout.super_hero_item,
            parent,
            false
        )
        return SuperHeroHolder(view)
    }

    override fun onBindViewHolder(holder: SuperHeroHolder, position: Int) {
        holder.view.result = heroList[position]
        holder.view.listener =this

    }

    override fun getItemCount(): Int {
        return heroList.size
    }

    fun setListItem(list: List<Result>){
        heroList.clear()
        heroList.addAll(list)
        notifyDataSetChanged()
    }

    override fun onSuperHeroClicked(view: View) {
        val resultId = view.resultIdTextView.text.toString().toLong()

        val action =SuperHeroesFragmentDirections.actionSuperHeroesToSuperHeroDetail(resultId)
        Navigation.findNavController(view).navigate(action)
    }
}

