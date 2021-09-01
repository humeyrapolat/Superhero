package com.humeyrapolat.superhero.view

import android.app.Activity
import android.app.SearchManager
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ContextMenu
import android.view.Menu
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.humeyrapolat.superhero.R
import com.humeyrapolat.superhero.adapter.SuperHeroesAdapter
import kotlinx.android.synthetic.main.fragment_super_hero_appearance.view.*
import kotlinx.android.synthetic.main.fragment_super_heroes.*
import java.util.ArrayList

/**
 * @author Humeyra Polat
 * @sınce 24.08.2021
 */
class MainActivity : AppCompatActivity() {


    private val superHeroesAdapter = SuperHeroesAdapter(arrayListOf())

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
     //   super_heroes_recycler.adapter = superHeroesAdapter



        navController = Navigation.findNavController(this, R.id.fragment)
        NavigationUI.setupActionBarWithNavController(this, navController)
    }

    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(navController, null)
    }


    @JvmName("Utils")
    fun Activity.hideSoftKeyboard() {
        currentFocus?.let {
            val inputMethodManager = ContextCompat.getSystemService(this, InputMethodManager::class.java)!!
            inputMethodManager.hideSoftInputFromWindow(it.windowToken, 0)
        }
    }


}

