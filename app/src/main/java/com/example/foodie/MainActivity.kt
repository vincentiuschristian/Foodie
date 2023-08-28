package com.example.foodie

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
            startActivity(Intent(this, AboutActivity::class.java))
            true
        }
        else -> {
            super.onOptionsItemSelected(item)
        }
    }

    private fun getListFood(): ArrayList<Foodie> {
        val dataName = resources.getStringArray(R.array.data_name)
        val dataTime = resources.getStringArray(R.array.cook_time)
        val dataServings = resources.getStringArray(R.array.servings)
        val imgFood = resources.obtainTypedArray(R.array.data_photo)
        val dataIng = resources.getStringArray(R.array.data_ingredients)
        val dataDirection = resources.getStringArray(R.array.data_direction)
        val dataCalories = resources.getStringArray(R.array.data_calories)
        val listFood = ArrayList<Foodie>()
        for (i in dataName.indices) {
            val food =
                Foodie(dataName[i], dataTime[i], dataServings[i], imgFood.getResourceId(i, -1), dataIng[i], dataDirection[i], dataCalories[i])
            listFood.add(food)
        }
        return listFood
    }

    private fun showRecyclerList() {
        binding.rvItem.layoutManager = LinearLayoutManager(this)
        val foodieAdapter = FoodieAdapter(list, object : FoodieAdapter.OnItemClickCallBack {
            override fun onItemClicked(data: Foodie) {
                val intentDetail = Intent(this@MainActivity, DetailActivity::class.java)
                intent.putExtra("FOOD", data)
                startActivity(intentDetail)
            }
        })
        binding.rvItem.adapter = foodieAdapter
    }

}