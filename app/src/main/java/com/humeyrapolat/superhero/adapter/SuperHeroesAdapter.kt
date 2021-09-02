package com.humeyrapolat.superhero.adapter

import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.MutableLiveData
import androidx.navigation.Navigation
import androidx.palette.graphics.Palette
import androidx.recyclerview.widget.RecyclerView
import com.humeyrapolat.superhero.model.Result
import com.humeyrapolat.superhero.model.SuperHeroListener
import com.humeyrapolat.superhero.R
import com.humeyrapolat.superhero.view.SuperHeroesFragmentDirections
import com.humeyrapolat.superhero.databinding.SuperHeroItemBinding
import com.humeyrapolat.superhero.view.SuperHeroAppearanceFragmentDirections
import kotlinx.android.synthetic.main.fragment_super_heroes.*
import kotlinx.android.synthetic.main.super_hero_item.view.*
import java.util.*
import kotlin.collections.ArrayList

/**
 * @author Humeyra Polat
 * @sınce 26.08.2021
 */
class SuperHeroesAdapter(var heroList: ArrayList<Result>) :
    RecyclerView.Adapter<SuperHeroesAdapter.SuperHeroHolder>(), SuperHeroListener, Filterable {

    var filteredList = ArrayList<Result>()
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
        holder.view.result = filteredList[position]
        holder.view.listener = this


    }

    override fun getItemCount(): Int {
        return filteredList.size
    }

    fun setListItem(list: List<Result>) {
        filteredList.clear()
        filteredList.addAll(list)
        heroList.addAll(filteredList)
        notifyDataSetChanged()
    }

    override fun onSuperHeroClicked(view: View) {
        val resultId = view.resultIdTextView.text.toString().toLong()

        val action = SuperHeroesFragmentDirections.actionSuperHeroesToSuperHeroDetail(resultId)
        Navigation.findNavController(view).navigate(action)
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(charSequence: CharSequence?): FilterResults {
                val filterResults = FilterResults()
                val key = charSequence.toString()
                if (key.isEmpty()) {
                    filteredList = heroList
                } else {
                    val isFilteredList = ArrayList<Result>()
                    for (row in heroList) {
                        if (row.name!!.toLowerCase(Locale.ROOT)
                                .contains(key.toLowerCase(Locale.ROOT))
                        ) {
                            isFilteredList.add(row)
                        }
                    }
                    filteredList = isFilteredList
                }

                filterResults.values = filteredList
                return filterResults
            }

            override fun publishResults(charSequence: CharSequence?, results: FilterResults?) {
                filteredList = results?.values as ArrayList<Result>
                notifyDataSetChanged()
            }

        }
    }

}

