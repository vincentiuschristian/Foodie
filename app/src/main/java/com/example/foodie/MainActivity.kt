package com.example.foodie

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.foodie.adapter.FoodieAdapter
import com.example.foodie.data.Foodie
import com.example.foodie.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val list = ArrayList<Foodie>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rvItem.setHasFixedSize(true)
        list.addAll(getListFood())

        showRecyclerList()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean = when (item.itemId) {
        R.id.btn_Profile -> {
            val moveProfileIntent = Intent(this@MainActivity, AboutPageActivity::class.java)
            moveProfileIntent.putExtra(AboutPageActivity.EXTRA_NAMA, "Vincentius Christian Chandra")
            moveProfileIntent.putExtra(AboutPageActivity.EXTRA_EMAIL, "vincentcc02@gmail.com")
            startActivity(moveProfileIntent)
            true
        }
        else -> {
            super.onOptionsItemSelected(item)
        }
    }

    @SuppressLint("Recycle")
    private fun getListFood(): ArrayList<Foodie> {
        val dataName = resources.getStringArray(R.array.data_name)
        val dataTime = resources.getStringArray(R.array.cook_time)
        val dataServings = resources.getStringArray(R.array.servings)
        val imgFood = resources.obtainTypedArray(R.array.data_photo)
        val dataIng = resources.getStringArray(R.array.data_ingredients)
        val dataInstructions = resources.getStringArray(R.array.data_instructions)
        val dataCalories = resources.getStringArray(R.array.data_calories)
        val listFood = ArrayList<Foodie>()
        for (i in dataName.indices) {
            val food =
                Foodie(dataName[i], dataTime[i], dataServings[i], imgFood.getResourceId(i, -1), dataIng[i], dataInstructions[i], dataCalories[i])
            listFood.add(food)
        }
        return listFood
    }

    private fun showRecyclerList() {
        binding.rvItem.layoutManager = LinearLayoutManager(this)
        val foodieAdapter = FoodieAdapter(list)
        binding.rvItem.adapter = foodieAdapter
    }
}